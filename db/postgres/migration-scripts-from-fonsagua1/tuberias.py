# -*- coding: utf-8 -*-

class CopyAttributesTuberias(CopyAttributes):
    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('CodigoAb')] != None)
    
    def specificData(self):
        tipologia = self.iatts[self.ilayer.fieldNameIndex('Tipo')]
        if (tipologia.startswith('Adu')):
            self.of.setAttribute('tipologia_tuberia', 'Aducci' + unichr(243) + 'n')
        else:
            if (tipologia.startswith('Impul')):
                self.of.setAttribute('tipologia_tuberia', 'Impulsi' + unichr(243) + 'n')
            else:
                if (tipologia.startswith('Distri')):
                    self.of.setAttribute('tipologia_tuberia', 'Distribuci' + unichr(243) + 'n')

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_tuberias'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'tuberias'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesTuberias(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_abastecimiento', 'CodigoAb')
            ca.copy('diametro', 'Diametro')
            ca.copy('estado', 'Estado')
            ca.copy('material', 'Material')
            ca.copy('cod_tuberia', 'CodigoTub')
            ca.copy('fugas', 'Fugas', ca.siNo2Chb)
            ca.copy('loc_fugas', 'FugLoc', lambda v: v if v else None)
            
            
            
            ca.specificData()
            
            # TODO
            #of.setAttribute('', iatts[ilayer.fieldNameIndex('CodigoCap')])
            
            newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"