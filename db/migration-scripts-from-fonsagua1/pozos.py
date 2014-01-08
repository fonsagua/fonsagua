# -*- coding: utf-8 -*-

class CopyAttributesPozos(CopyAttributes):

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
        
        self.of.setAttribute('tipo_fuente', 'Pozo')
        return None

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_pozos'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'fuentes'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesPozos(ifeat, ofields, ilayer)
        if (ca.validRow()):
            # El codigo 06072401P01 está repetido, se le pone a uno de ellos 06072401P03
            ca.copy('cod_fuente', 'IdFuente')
            ca.copy('comunidad', 'CodigoC')
            ca.copy('fuente', 'Nombre')
            ca.copy('altura_brocal', 'AlturaBroc')
            ca.copy('prof_pozo', 'ProfPozo')
            ca.copy('escritura', 'ConEscritu', ca.siNo2Chb)
            ca.copy('uso', 'SeUsa', ca.siNo2Chb)
            ca.copy('naci_terremoto', 'AparecTerr', ca.siNo2Chb)
            ca.copy('cambios_terremoto', 'VariaQTerr', ca.siNo2Chb)
            ca.copy('comentarios', 'Coment')
            ca.copy('prof_bomba', 'ProfBomba')
            ca.copy('limpiezas', 'Limpiezas', ca.siNo2Chb)
            ca.copy('rep_metodo', 'CuantLimpi')
            ca.copy('diametro_interior', 'Diametro')
            ca.copy('dist_linea_electrica', 'dist_elec', ca.str2meters)
            ca.copy('utm_x', 'x')
            ca.copy('utm_y', 'y')
            ca.copy('utm_z', 'Altura')
            ca.copy('nom_propietario', 'Propietari')
            ca.copy('reperforado', 'ProfSubcc', lambda v: 'true' if v else 'false')
            ca.copy('rep_distancia', 'ProfSubcc')
            
            # TODO
            #ca.copy('', 'AlturaC')            
            #ca.copy('', 'AltCaptVia')
            #ca.copy('', 'FuncVerano')
            #ca.copy('', 'UsoOficio')
            #ca.copy('', 'UsoLavarR')
            #ca.copy('', 'AseoPerson')
            #ca.copy('', 'OtrosUsos')
            #ca.copy('', 'UsoBebida')
            
            # Éstos no parecen tener datos
            #ca.copy('', 'AgrInv')
            #ca.copy('', 'AgrVeran')
            #ca.copy('', 'GanadInv')
            #ca.copy('', 'GanadVeran')
            #ca.copy('', 'DomesInv')
            #ca.copy('', 'DomestVera')
            #ca.copy('', 'EnsayosBom')
            
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