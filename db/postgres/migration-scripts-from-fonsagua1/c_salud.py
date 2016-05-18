# -*- coding: utf-8 -*-


class CopyAttributesCSalud(CopyAttributes):
  
    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('CodigoC')] != None)


ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_csalud'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'centros_salud'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesCSalud(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_c_salud', 'CodCSal')
            ca.copy('cod_comunidad', 'CodigoC')
            ca.copy('nombre', 'Nombre')
            ca.copy('utm_x', 'x')
            ca.copy('utm_y', 'y')
            ca.copy('utm_z', 'z', ca.toZ)
            
            
            newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"