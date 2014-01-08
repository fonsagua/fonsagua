# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades_interlocutores'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'entrevistados'][0]
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
        ca.copy('cargo', 'CargoEntrv')
        ca.copy('nombre', 'Entrev')
        ca.copy('telefono', 'Telefono')
        ca.copy('fecha', 'Fecha', lambda v: v.replace('-', '/'))
        
        auxCodes[ifeat.attributes()[0]] = True
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    res = True
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return

myfunction()