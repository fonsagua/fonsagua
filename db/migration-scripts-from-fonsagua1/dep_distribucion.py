# -*- coding: utf-8 -*-

class CopyAttributesDepDistribucion(CopyAttributes):
  
    def specificData(self):
        if (self.iatts[self.ilayer.fieldNameIndex('TipoConstr')] == 'Tanque ladrill rafn armado'):
            self.of.setAttribute('tipo_construccion', 'Ladrillo')
        else:
            self.of.setAttribute('tipo_construccion', self.iatts[self.ilayer.fieldNameIndex('TipoConstr')])
        
        return None

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_depdistrib'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'dep_distribucion'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesDepDistribucion(ifeat, ofields, ilayer)
        ca.copy('cod_abastecimiento', 'CodigoDepD', ca.toCodigoAB)
        
        # Los códigos 06070301T01, 06070701T01 y 06070901T01 están repetidos
        # y se eliminan a mano del original
        ca.copy('cod_dep_distribucion', 'CodigoDepD')
        ca.copy('estado', 'Estado')
        ca.copy('volumen', 'Capacidad', ca.gal2metroc)
        ca.copy('t_llenado', 'HorasLlen')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('utm_z', 'z')
        
        ca.specificData()
        newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "Error guardando la capa"
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"