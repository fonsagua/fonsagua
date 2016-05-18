# -*- coding: utf-8 -*-

def updateComunidades(auxCodes):
    comunidades = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0]
    comunidades.dataProvider().clearErrors()
    comunidadesFields = comunidades.dataProvider().fields()
    comunidadesIdAtts = {}
    for feature in comunidades.getFeatures():
        idx = comunidades.fieldNameIndex('cod_comunidad')
        if feature.attributes()[idx] in auxCodes:
            comunidadesIdAtts[feature.id()] = {comunidades.fieldNameIndex('h_cooperativas'):'true'}
    r = comunidades.dataProvider().changeAttributeValues(comunidadesIdAtts)
    if not r:
        print 'Error'


def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades_riegoorganizacion'][0]
    olayer  = [x for x in iface.legendInterface().layers() if x.name() == 'cooperativas'][0]
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
        ca.copy('cod_comunidad', 'CodigoC')
        ca.copy('tipo', 'TipoOrg')
        ca.copy('n_asociados', 'NAsoc', ca.v2int)
        ca.copy('rubros', 'Rubros')
        ca.copy('recursos', 'Recursos')
        
        codcom = ifeat.attributes()[0]
        auxCodes[codcom] = True
        if codcom not in codigoscomunidad:
            print 'El codigo no es valido %s' % (codcom)
            return
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return

    updateComunidades(auxCodes)
    

myfunction()