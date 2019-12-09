import collections
import json
import os


def get_municipios(basepath, filepath):
    j = json.loads(open(os.path.join(basepath, "config.json")).read())

    municipios = collections.OrderedDict()
    for m in j["municipios_order"]:
        municipios[m] = j["_municipios"][m]

    if basepath:
        for k in municipios:
            municipios[k]["path"] = os.path.join(basepath, k, "fonsagua.sqlite")

    todos = {"path": filepath, "init_gid": 0}

    return municipios, todos


def get_municipios_nasmar_codigos():
    return sorted(m["cod_municipio"] for m in get_municipios(None, None)[0].values())


def get_fonsagua_app_tablenames():

    folder = os.path.dirname(os.path.realpath(__file__))
    filepath = os.path.join(folder, "table_list/fonsagua_app_tables.txt")
    with open(filepath) as f:
        tables = [t.strip() for t in f]
    return tables


def get_fonsagua_app_tables():
    folder = os.path.dirname(os.path.realpath(__file__))
    filepath = os.path.join(folder, "table_list/fonsagua_app_tables_con_codigos.txt")
    tables = collections.OrderedDict()
    with open(filepath) as f:
        for r in f:
            tokens = r.strip().split()
            tables[tokens[0]] = tokens[1:]

    return tables
