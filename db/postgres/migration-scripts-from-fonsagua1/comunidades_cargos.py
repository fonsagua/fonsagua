# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades_cargos'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'cargos_publicos'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    auxCodes = {}
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_comunidad', 'CodigoC')
        ca.copy('nombre', 'Nombre')
        ca.copy('cargo', 'Cargo')
        auxCodes[ifeat.attributes()[0]] = True
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return


    comunidades = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0]
    comunidades.dataProvider().clearErrors()
    comunidadesFields = comunidades.dataProvider().fields()
    comunidadesIdAtts = {}
    for feature in comunidades.getFeatures():
        idx = comunidades.fieldNameIndex('cod_comunidad')
        if feature.attributes()[idx] in auxCodes:
            comunidadesIdAtts[feature.id()] = {comunidades.fieldNameIndex('h_cargos_publicos'):'true'}
    r = comunidades.dataProvider().changeAttributeValues(comunidadesIdAtts)
    if not r:
        print 'Error'


myfunction()