#!/usr/bin/env python36

import argparse
import collections
import logging
import os

from ietl.report_utils import to_csv
from ietl.spatialite_utils import get_spatialite_conn
from municipios_utils import get_fonsagua_app_tables, get_municipios


logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


def get_results(sqlitepath, tables, cod_municipio=None, delete=False):
    conn = get_spatialite_conn(sqlitepath)
    cursor = conn.cursor()
    results = collections.OrderedDict()
    for table, codes in tables.items():
        code_str = ", ".join(codes)
        results[table] = {"code_names": codes}
        sql = f"SELECT {code_str}, count(*) FROM {table} GROUP BY {code_str} ORDER BY {code_str}"
        cursor.execute(sql)
        results[table]["all"] = [list(k) for k in cursor]

        if cod_municipio:
            code_name_to_check_municipio = codes[0]
            code_values_to_check_municipio = [v[0] for v in results[table]["all"]]
            bad_code_vales_for_municipio = [
                v for v in code_values_to_check_municipio if v[0:4] != cod_municipio
            ]
            results[table][
                "bad_code_vales_for_municipio"
            ] = bad_code_vales_for_municipio
            if bad_code_vales_for_municipio:
                print(
                    "Código no correspondiente al municipio",
                    sqlitepath,
                    table,
                    code_name_to_check_municipio,
                    bad_code_vales_for_municipio,
                )
            if delete and bad_code_vales_for_municipio:
                in_clause_questionmarks = ",".join(
                    "?" * len(bad_code_vales_for_municipio)
                )
                # sql=f"DELETE FROM {table} WHERE {code_name_to_check_municipio} IN ({in_clause_questionmarks});"
                sql = f"DELETE FROM {table} WHERE {code_name_to_check_municipio} IN ('17090601', '170906AL01', '17010809', '170906FU01', '170906FU02', '170906FU03', '170906FU04', '170906FU05', '170906FU06', '170906FU07');"
                # print(sql)
                # cursor.execute(sql, bad_code_vales_for_municipio)
                cursor.execute(sql)
                conn.commit()

    cursor.close()
    return results


def write_all_codes_book(results, filename, outputodsfolder):
    import pyexcel

    bookdict = {}
    for foo in results:
        header = [*results[foo]["code_names"], "conteo"]
        values = results[foo]["all"][:]
        values.insert(0, header)
        bookdict[foo] = values
    filepath = filename
    if outputodsfolder:
        filepath = os.path.join(outputodsfolder, filename)
    pyexcel.save_book_as(bookdict=bookdict, dest_file_name=filepath)


def main(filepath, basepath, delete, outputodsfolder):
    tables = get_fonsagua_app_tables()

    header = ["Tabla", "BD"]

    municipios, todos = get_municipios(basepath, filepath)

    todos["count"] = get_results(filepath, tables)

    final = [tables, todos["count"]]
    if basepath:
        mun_count = []
        for k in municipios:
            # if k != "200312_eltriunfo":
            #     continue
            print(k)
            municipios[k]["path"] = os.path.join(basepath, k, "fonsagua.sqlite")
            municipios[k]["count"] = get_results(
                municipios[k]["path"], tables, municipios[k]["cod_municipio"], delete
            )
            write_all_codes_book(
                municipios[k]["count"],
                f"{k}_{municipios[k]['cod_municipio']}.ods",
                outputodsfolder,
            )

            # mun_count.append(municipios[k]['count'])
            # header.append(k)
        # header.extend(["Suma", "Diferencia"])
        # mun_count.append([sum(y) for y in zip(*mun_count)])
        # mun_count.append([x - y for x, y in zip(todos['count'], mun_count[-1])])
        # final.extend(mun_count)

    # results = list(zip(*final))
    # results.insert(0, header)
    # to_csv('conteo_elementos_bds.csv', results, header)

    write_all_codes_book(todos["count"], "todas.ods", outputodsfolder)


if __name__ == "__main__":
    description = """
    A pesar de que aparezca como opcional es necesario proporcionar basepath y
    filepath. filepath puede ser una base de datos cualquiera (por ejemplo una 
    dentro de basepath).
    
    Por completitud faltaría poder volcar a una hoja de cálculo los códigos que
    hay en una bd y que no se corresponden a ese municipio
    """
    parser = argparse.ArgumentParser(description=description)
    parser.add_argument("filepath", help="Path al sqlite")
    parser.add_argument("--basepath")
    parser.add_argument(
        "--delete",
        action="store_true",
        help="Elimina los elementos que no se correspondan a ese municipio",
    )
    parser.add_argument(
        "--outputodsfolder",
        help="Path al directorio donde se almacenarán la información de depuración de salida y que debe existir",
    )
    args = parser.parse_args()
    results = main(args.filepath, args.basepath, args.delete, args.outputodsfolder)
    # print(results)
