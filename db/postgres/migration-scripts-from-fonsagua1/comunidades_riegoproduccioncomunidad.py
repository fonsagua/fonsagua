# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades_riegoproduccioncomunidad'][0]
    cultivoslayer  = [x for x in iface.legendInterface().layers() if x.name() == 'tipos_cultivos'][0]
    cultivoslayer.dataProvider().clearErrors()
    ganaderialayer  = [x for x in iface.legendInterface().layers() if x.name() == 'ganaderia'][0]
    ganaderialayer.dataProvider().clearErrors()
    
    caps = cultivoslayer.dataProvider().capabilities()

    cultivosfields = cultivoslayer.dataProvider().fields()
    ganaderiafields = ganaderialayer.dataProvider().fields()

    codigoscomunidad = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0].dataProvider().uniqueValues(1)
     
    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    ganaderia = []
    cultivos = []
    auxCodes = {}
    for ifeat in ilayer.getFeatures():
        ca = None
        if ifeat.attributes()[1].upper().startswith('B'):
            ca = CopyAttributes(ifeat, ganaderiafields, ilayer)
            ca.copy('cod_comunidad', 'CodigoC')
            ca.copy('tipo', 'Tipo')
            ca.copy('f_propietarias', 'NFamProp')
            ca.copy('f_arrendatarias', 'NFamArrend')
            ca.copy('areafam', 'AreaFam')
            ca.copy('rendimiento', 'Rendim')
            ca.copy('n_familias', 'NumFam')
            ca.copy('rubro', 'Rubro')
            ganaderia.append(ca.getNewFeature())
        else:
            ca = CopyAttributes(ifeat, cultivosfields, ilayer)
            ca.copy('cod_comunidad', 'CodigoC')
            ca.copy('tipo', 'Tipo')
            ca.copy('f_propietarias', 'NFamProp')
            ca.copy('f_arrendatarias', 'NFamArrend')
            ca.copy('superficie', 'AreaFam')
            ca.copy('rendimiento', 'Rendim')
            ca.copy('n_familias', 'NumFam')
            ca.copy('rubro', 'Rubro')
            cultivos.append(ca.getNewFeature())
        
        codcom = ifeat.attributes()[0]
        auxCodes[codcom] = True
        if codcom not in codigoscomunidad:
            print 'El codigo no es valido %s' % (codcom)
            return
        # print ca.getNewFeature().attributes()
        

    (res, foo) = cultivoslayer.dataProvider().addFeatures(cultivos)
    if not res:
        print "************** Error guardando la capa ********* "
        print cultivoslayer.dataProvider().errors()
        return

    (res, foo) = ganaderialayer.dataProvider().addFeatures(ganaderia)
    if not res:
        print "************** Error guardando la capa ********* "
        print ganaderialayer.dataProvider().errors()
        return

myfunction()