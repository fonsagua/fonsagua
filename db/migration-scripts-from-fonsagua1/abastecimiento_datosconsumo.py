# -*- coding: utf-8 -*-

from PyQt4.QtCore import QPyNullVariant

import sqlite3

def myfunction():
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'datos_consumo'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()
    ofields = olayer.dataProvider().fields()
   
    conn = sqlite3.connect('/home/fpuga/Escritorio/migracion/130918_data_Marcovia/_DB/fonsagua.sqlite3')
    c = conn.cursor()
    newFeatures = []
    for row in c.execute('SELECT codigoab, usosagua, nmiembros, (vdeposito * nveces)*1000 FROM abastecimiento_datosconsumo'):
        of = QgsFeature()
        of.setFields(ofields)
        of.setAttribute('cod_comunidad', '06072701E01' if row[0] == '06072701' else row[0])
        of.setAttribute('cod_abastecimiento', row[0])
        of.setAttribute('usos_agua', row[1])
        of.setAttribute('n_miembros', row[2])
        of.setAttribute('consumo', row[3])
        (res, outFeats) = olayer.dataProvider().addFeatures( [of] )
        if not res:
            print "************** Error guardando la capa ********* "
            print olayer.dataProvider().errors()
            print of.attributes()
            olayer.dataProvider().clearErrors()

myfunction()