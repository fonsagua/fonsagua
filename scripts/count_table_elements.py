#!/usr/bin/env python36

import argparse
import logging
import os

from ietl.report_utils import to_csv
from ietl.spatialite_utils import get_spatialite_conn
from municipios_utils import get_fonsagua_app_tablenames, get_municipios


logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


def get_results(sqlitepath, tables):
    conn = get_spatialite_conn(sqlitepath)
    cursor = conn.cursor()
    results = []
    for t in tables:
        sql = f"SELECT count(*) FROM {t}"
        count = cursor.execute(sql).fetchone()[0]
        results.append(count)
    cursor.close()
    return results


def main(filepath, basepath, reportpath):
    tables = get_fonsagua_app_tablenames()

    header = ["Tabla", "BD"]

    municipios, todos = get_municipios(basepath, filepath)

    todos["count"] = get_results(filepath, tables)
    final = [tables, todos["count"]]

    if basepath:
        mun_count = []
        for k in municipios:
            print(k)
            municipios[k]["count"] = get_results(municipios[k]["path"], tables)
            mun_count.append(municipios[k]["count"])
            header.append(k)
        header.extend(["Suma", "Diferencia"])
        mun_count.append([sum(y) for y in zip(*mun_count)])
        mun_count.append([x - y for x, y in zip(todos["count"], mun_count[-1])])
        final.extend(mun_count)

    results = list(zip(*final))
    # results.insert(0, header)
    filepath = "conteo_elementos_bds.csv"
    if reportpath:
        filepath = os.path.join(reportpath, filepath)
    to_csv(filepath, results, header)
    return results


if __name__ == "__main__":
    description = """
    Si se proporciona `basepath` se usa para obtener el número de elementos de
    las bases de datos sqlite que se encuentren en subdirectorios y filepath
    se corresponderá con una que tenga todos
    """
    parser = argparse.ArgumentParser(description=description)
    parser.add_argument("--filepath", help="Path al sqlite")
    parser.add_argument("--basepath")
    parser.add_argument("--reportpath")
    args = parser.parse_args()

    if not args.filepath and not args.basepath:
        print("filepath o basepath son necesarios")

    results = main(args.filepath, args.basepath, args.reportpath)
    # print (results)
