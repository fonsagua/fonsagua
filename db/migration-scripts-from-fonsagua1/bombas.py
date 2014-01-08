# -*- coding: utf-8 -*-

class CopyAttributesBombas(CopyAttributes):
   
    def specificData(self):
        energia = self.iatts[self.ilayer.fieldNameIndex('Energia')]
        if (energia == 'Motor diesel'):
            self.of.setAttribute('energia', 'Motor Di' + unichr(233) + 'sel')
        else:
            if (energia == 'Red elctrica'):
                self.of.setAttribute('energia', 'Red El' + unichr(233) + 'ctrica')
            else:
                self.of.setAttribute('energia', energia)

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_bombas'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'bombeos'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesBombas(ifeat, ofields, ilayer)
        
        ca.copy('cod_abastecimiento', 'CodigB', ca.toCodigoAB)
        ca.copy('cod_bombeo', 'CodigB')
        ca.copy('tipologia_bomba', 'Tipo')
        ca.copy('potencia', 'Potencia')
        ca.copy('caudal', 'Caudal')
        ca.copy('tiempo', 'HorasBomb')
        ca.copy('prof_succion', 'Subccion')
        ca.copy('estado', 'Estado')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('utm_z', 'z')
        
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