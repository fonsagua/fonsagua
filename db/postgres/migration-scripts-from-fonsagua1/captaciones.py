# -*- coding: utf-8 -*-

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_captacion'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'captaciones'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
       
        ca.copy('cod_abastecimiento', 'CodigoCap', ca.toCodigoAB)

        ca.copy('cod_captacion', 'CodigoCap')
        ca.copy('sistema', 'Sistema')
        ca.copy('estado', 'Estado')
        ca.copy('volumen', 'Volumen', ca.gal2metroc)
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('utm_z', 'z', ca.toZ)
        
        
        # Contiene la misma información que sistema
        #ca.copy('tipo_fuente', 'Tipo')
        
        newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "Error guardando la capa"
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"