# -*- coding: utf-8 -*-

def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_ccomunit'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'otros_servicios'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    
    for ifeat in ilayer.getFeatures():
        # Si no tiene el codigo de comunidad, es un dato erroneo
        if not ifeat.attributes()[1]:
            continue
            
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('nombre', 'Nombre')
        ca.copy('cod_servicio', 'id_ccomun')
        ca.copy('cod_comunidad', 'id_ccomun', ca.toCodigoC)
        ca.copy('tipo_servicio', 'Tipo', lambda v: 'Otros' if not v else 'Iglesia' if v.startswith('Igle') else u'Instalaci\xf3n deportiva' if v.startswith('Insta') else 'Centro comunitario' if v.startswith('Casa') else 'Otros')
        ca.copy('utm_z', 'z')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        
        
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return
        
myfunction()