# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_relacion_comunidades_abastecimientos'][0]
    olayer  = [x for x in iface.legendInterface().layers() if x.name() == 'r_abastecimientos_comunidades'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    codigoscomunidad = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0].dataProvider().uniqueValues(1)
     
    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    auxCodes = {}
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_comunidad', 'codigoc')
        ca.copy('cod_abastecimiento', 'codigoab')
        
        codcom = ifeat.attributes()[0]
        auxCodes[codcom] = True
        if codcom not in codigoscomunidad:
            print 'El codigo no es valido %s' % (codcom)
            return
        
        # print ca.getNewFeature().attributes()
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return


myfunction()