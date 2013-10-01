#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys
import imp
import pg

def resultToArray(result):
    array = []
    for t in result:
        array.append(t[0])
    return array


m = imp.load_source('', sys.argv[1])
con  = pg.connect(dbname = m.dbname, host = m.server, user = m.user, passwd = m.userpwd)
schema = "fonsagua"
query = "select tablename from pg_tables where schemaname='" + schema + "';"
tables = [' --table ' + t[0] for t in con.query(query).getresult() ]
con.close()

print 

command = './pg_dump -U postgres --column-inserts --data-only ' + ' '.join(tables) + ' --file=/tmp/test-data-fonsagua.sql ' + m.dbname
print command
# ./pg_dump -U postgres --column-inserts --data-only --table comunidades --table puntos_viviendas --table areas_potenciales_riego --table centros_educativos --table centros_salud --table otros_servicios --table amenazas --table fuentes_contaminacion --table abastecimientos --table captaciones --table dep_intermedios --table dep_distribucion --table tuberias --table bombeos --table fuentes --file=/tmp/data-test-geometry-tables.sql fonsagua





pg_dump -U postgres --column-inserts --data-only --schema=fonsagua --file=/tmp/`date +%Y%m%d`-fonsagua-test-data.sql fonsagua

 pg_dump -U psanxiao -h demiurgo --column-inserts --data-only --schema=fonsagua --file=/tmp/`date +%Y%m%d`-fonsagua-test-data.sql fonsagua_20130527
