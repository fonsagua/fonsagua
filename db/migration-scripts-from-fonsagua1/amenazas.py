# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_amenazas'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'amenazas'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_amenaza', 'CodigoFR')
        ca.copy('tipo_amenaza', 'TipoFR', lambda v: 'Deslizamientos' if v.startswith('Desliza') else u'Desbordamiento de r\xedo' if v.startswith('Desbor') else 'Inundaciones' if v.startswith('Inun') else 'Puntos de asalto' if v.startswith('Inse') else 'Otros')
        ca.copy('n_fam_afectadas', 'FamiliasAf')
        ca.copy('utm_z', 'Altura')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('cod_comunidad', 'codigoc')
        
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    res = True
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return


myfunction()