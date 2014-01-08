# -*- coding: utf-8 -*-

class CopyAttributesAforosPozos(CopyAttributes):

    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('idfuente')] != None)
    


ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_abastecimiento_aforopozo'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'aforos'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesAforosPozos(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_fuente', 'idfuente')
            ca.copy('aforo', 'aforo', ca.gal2metroc)
            ca.copy('fecha', 'fecha', lambda v: v.replace('-', '/') if v else None)
            
            newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"