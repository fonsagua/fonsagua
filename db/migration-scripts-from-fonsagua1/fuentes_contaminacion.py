# -*- coding: utf-8 -*-

class CopyAttributesFContaminacion(CopyAttributes):
   
    def specificData(self):
        if (self.iatts[self.ilayer.fieldNameIndex('TipoFC')] == 'Puntos de lavado'):
            self.of.setAttribute('tipo_contaminacion', 'Puntos de lavado y ba' + unichr(241) + 'o')
        else:
            if (self.iatts[self.ilayer.fieldNameIndex('TipoFC')] == 'Otros puntos de vertido de aguas residuales'):
                self.of.setAttribute('tipo_contaminacion', 'Puntos de vertido a r' + unichr(237) + 'o')
            else:
                self.of.setAttribute('tipo_contaminacion', self.iatts[self.ilayer.fieldNameIndex('TipoConstr')])
        
        return None

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_fcontaminacion'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'fuentes_contaminacion'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesFContaminacion(ifeat, ofields, ilayer)
        ca.copy('cod_comunidad', 'CodigoFC', ca.toCodigoC)
        ca.copy('descripcion', 'Descripc')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('utm_z', 'Altura', ca.toZ)
        
        ca.specificData()
        
        newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"