# -*- coding: utf-8 -*-

from PyQt4.QtCore import QPyNullVariant

import sqlite3

def myfunction():
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'habitos_consumo'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()
    ofields = olayer.dataProvider().fields()
   
    conn = sqlite3.connect(SQLITE_HONDURAS_PATH)
    c = conn.cursor()
    newFeatures = []
    for row in c.execute('SELECT codigoc, usos, nmiembrosf, (vdeposito * nvecesli)*1000 FROM noabastecimiento_habitosmedios'):
        of = QgsFeature()
        of.setFields(ofields)
        of.setAttribute('cod_comunidad', row[0])
        of.setAttribute('n_miembros', row[2])
        of.setAttribute('consumo', row[3])
        (res, outFeats) = olayer.dataProvider().addFeatures( [of] )
        if not res:
            print "************** Error guardando la capa ********* "
            print olayer.dataProvider().errors()
            print of.attributes()
            olayer.dataProvider().clearErrors()

myfunction()