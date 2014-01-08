# -*- coding: utf-8 -*-


class CopyAttributesRios(CopyAttributes):
  
    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('codigoc')] != None)
    
    def specificData(self):
        self.of.setAttribute('tipo_fuente', 'Punto rio')
        return None

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_ptos_rios'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'fuentes'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesRios(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_fuente', 'IdFuente')
            ca.copy('comunidad', 'codigoc')
            ca.copy('fuente', 'Nombre')
            ca.copy('utm_z', 'Altura')
            #ca.copy('', 'AlturaC') Eliminado a conciencia
            #ca.copy('', 'FuncVerano') TODO
            #ca.copy('', 'AlterCViab') TODO
            ca.copy('uso', 'SeUtiliza', ca.siNo2Chb)
            #ca.copy('', 'UsoOficio') TODO
            #ca.copy('', 'UsoLavarRo') TODO
            #ca.copy('', 'AseoPerson') TODO
            #ca.copy('', 'OtrosUsos') TODO
            #ca.copy('', 'UsoBebida') TODO
            ca.copy('comentarios', 'Coment')
            ca.copy('dist_linea_electrica', 'dist_elec', ca.str2meters)
            ca.copy('utm_x', 'x')
            ca.copy('utm_y', 'y')
            
            # Éstos no tienen datos. Se elminan a conciencia
            #ca.copy('', 'AgrInv')
            #ca.copy('', 'AgrVeran')
            #ca.copy('', 'GanadInv')
            #ca.copy('', 'GanadVeran')
            #ca.copy('', 'DomesInv')
            #ca.copy('', 'DomestVera')
            
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