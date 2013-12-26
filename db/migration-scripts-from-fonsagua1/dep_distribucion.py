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
        return (self.iatts[self.ilayer.fieldNameIndex('CodigoAb')] != None)
    
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
        ca = CopyAttributes(ifeat, ofields, ilayer)
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