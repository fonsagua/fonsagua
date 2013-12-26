# -*- coding: utf-8 -*-

from PyQt4.QtCore import QPyNullVariant

class CopyAttributes():
    def __init__(self, ifeat, ofields, ilayer):
        self.ilayer = ilayer
        self.iatts = ifeat.attributes()
        self.of = QgsFeature()
        self.of.setFields(ofields)
        self.of.setGeometry(ifeat.geometry())

        self.codigoscomunidad = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0].dataProvider().uniqueValues(1)
        self.codigosabastecimiento = [x for x in iface.legendInterface().layers() if x.name() == 'abastecimientos'][0].dataProvider().uniqueValues(0)

    def copy(self, o, i, processfunction=None):
        '''
        copy of inputFeature.i attr to ouputFeature.o attr
        if processfunction is provided applies it first to to the value of i
        '''
        ivalue = self.iatts[self.ilayer.fieldNameIndex(i)]
        

        if processfunction:
            self.of.setAttribute(o, processfunction(ivalue))
        else:
            self.of.setAttribute(o, ivalue)

    def siNo2Chb(self, str):
        '''
        str should be 'Si/No/Null) and returns 'true/false'
        uses startswith to avoid encoding problems
        '''
        if str and str.startswith('S'):
            return 'true'
        return 'false'

    def v2int(self, v):
        '''
        must be improved, the idea is convert float to int or liberal strings to int
        '''
        if v:
            return int(v)
        return 0
    
    def str2meters(self, v):
        try:
            return int(v)
        except ValueError:
            if (v.endswith('km')):
                return int(v[:-2]) * 1000
            else:
                if (v.endswith('metros')):
                    return int(v[:-6])
                else:
                    if (v.endswith('m')):
                        return int(v[:-1])
            return None
        except TypeError:
            return v
    
    def isTrue(self, v):
        if v:
            return 'true'
        return 'false'

    def toCodigoC(self, v):
        '''
        takes 8 characters from v and use it as codigoc if that value is valid codigoc
        if not COMUNIDAD_FALSA is used as codigoc
        '''
        codigoc = v[0:8]
        if codigoc in self.codigoscomunidad:
            return codigoc
        return 'COMUNIDAD_FALSA'
    
    def toCodigoAB(self, v):
        '''
        takes 8 characters from v and use it as codigoc if that value is valid codigoab
        if not DUMB is used as codigoab
        '''
        codigoab = v[0:8]
        if codigoab in self.codigosabastecimiento:
            return codigoab
        return 'DUMB'
    
    def gal2metroc(self, v):
        if v:
            return v/264.17
        return 0

    def getNewFeature(self):
        return self.of

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
        ca = CopyAttributes(ifeat, ofields, ilayer)
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

            # TODO
            #ca.copy('', 'AlturaCom')
            #ca.copy('', 'FuncionVer')
            #ca.copy('', 'Alternativ')
            #ca.copy('', 'UsoOficio')
            #ca.copy('', 'UsoLavarRo')
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