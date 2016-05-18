# -*- coding: utf-8 -*-

def updateComunidades(auxCodes):
    comunidades = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0]
    comunidades.dataProvider().clearErrors()
    comunidadesFields = comunidades.dataProvider().fields()
    comunidadesIdAtts = {}
    for feature in comunidades.getFeatures():
        idx = comunidades.fieldNameIndex('cod_comunidad')
        if feature.attributes()[idx] in auxCodes:
            comunidadesIdAtts[feature.id()] = {comunidades.fieldNameIndex('exp_ongs'):'true'}
    r = comunidades.dataProvider().changeAttributeValues(comunidadesIdAtts)
    if not r:
        print 'Error'


def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades_experienciasprevias'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'ongs'][0]
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
        ca.copy('fechas', 'fechas')
        ca.copy('ong', 'ong')
        ca.copy('tipo_capac', 'capacit', lambda v: v if v and v.upper() != 'NO' and v.replace('-', '') else None)
        ca.copy('tipo_proy', 'tipoproy')
        ca.copy('capacitacion', 'capacit', lambda v: 'true' if v and v.upper() != 'NO' and v.replace('-', '') else 'false')
        ca.copy('valoracion', 'valoracion', lambda v: 'Bien' if not v or v[0].upper() == 'B' else 'Mal')
        
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