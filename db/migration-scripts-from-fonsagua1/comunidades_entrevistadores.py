# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades_entrevistadores'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'entrevistadores'][0]
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
        ca.copy('nombre', 'Entrevist')
        ca.copy('cargo', 'Cargo')
        ca.copy('telefono', 'Telefono')
        ca.copy('instit', 'Instit')
        
        auxCodes[ifeat.attributes()[0]] = True
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    res = True
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return

myfunction()