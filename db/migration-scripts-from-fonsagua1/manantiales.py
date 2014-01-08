# -*- coding: utf-8 -*-

class CopyAttributesManantiales(CopyAttributes):

    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('CodigoC')] != None)
    
    def specificData(self):
        prop = self.iatts[self.ilayer.fieldNameIndex('Propietari')]
        if (('comunidad' in prop.lower()) or ('comunitari' in prop.lower())):
            self.of.setAttribute('propietario', 'Comunitario')
            self.of.setAttribute('nom_propietario', '')
        else:
            if ((prop != None) and (len(prop) > 0)):
                self.of.setAttribute('propietario', 'Privado')
        
        self.of.setAttribute('tipo_fuente', 'Manantial')
        return None

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_manantiales'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'fuentes'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesManantiales(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_fuente', 'IdFuente')
            ca.copy('comunidad', 'codigoc')
            ca.copy('fuente', 'Nombre')
            ca.copy('escritura', 'ConEscritu', ca.siNo2Chb)
            ca.copy('uso', 'SeUtiliza', ca.siNo2Chb)
            ca.copy('naci_terremoto', 'AparecioTe', ca.siNo2Chb)
            ca.copy('cambios_terremoto', 'VarioQTerr', ca.siNo2Chb)
            ca.copy('comentarios', 'Coment')
            ca.copy('dist_linea_electrica', 'dist_elec', ca.str2meters)
            ca.copy('utm_x', 'x')
            ca.copy('utm_y', 'y')
            ca.copy('utm_z', 'Altura')
            ca.copy('nom_propietario', 'Propietari')
            ca.copy('uso_bebida', 'UsoBebida', ca.siNo2Chb)

            # TODO
            
            #ca.copy('', 'FuncionVer')
            #ca.copy('', 'Alternativ')
            
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