# -*- coding: utf-8 -*-

class CopyAttributesCapacitacionesRiesgos(CopyAttributes):

    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('codigoc')] != None)


ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_factriesgo_capacitaciones'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'capacitaciones_riesgos'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesCapacitacionesRiesgos(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_comunidad', 'codigoc')
            ca.copy('temas', 'temas')
            ca.copy('fecha', 'fecha')
            ca.copy('institucion', 'instit')
            
            newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"