# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_puntorepr'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'puntos_viviendas'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()
    duplicatedCodigoC = []
    existent = []
    for ifeat in ilayer.getFeatures():
        cod = ifeat.attributes()[6]
        if  cod in existent:
            duplicatedCodigoC.append(cod)
        else:
            existent.append(cod)
    del existent
    del ifeat
    
    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    
    for ifeat in ilayer.getFeatures():
        if ifeat.attributes()[6] in duplicatedCodigoC:
            if not ifeat.attributes()[0]:
                continue
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_vivienda', 'id_ptorepr')
        ca.copy('cod_comunidad', 'id_ptorepr', ca.toCodigoC)
        ca.copy('tipo', 'Tipo', lambda v: 'Casa mas alta' if v=='Alto' else 'Casa mas baja' if v=='Bajo' else 'Otras casas' )
        ca.copy('utm_z', 'Altitud')
        ca.copy('descripcion', 'Descripc')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return


myfunction()