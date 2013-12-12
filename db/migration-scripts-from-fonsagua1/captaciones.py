# -*- coding: utf-8 -*-
import processing

from PyQt4.QtCore import QPyNullVariant

class CopyAttributes():
    def __init__(self, ifeat, ofields, ilayer):
        self.ilayer = ilayer
        self.iatts = ifeat.attributes()
        self.of = QgsFeature()
        self.of.setFields(ofields)
        self.of.setGeometry(ifeat.geometry())

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
        
    def getNewFeature(self):
        return self.of

    def validRow(self):
        return (self.iatts[self.ilayer.fieldNameIndex('CodigoAb')] != None)
    
    def specificData(self):
        return None

ilayer = processing.getobject("captacion")
olayer = processing.getobject("captaciones")
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        if (not ca.validRow()):
            # Las captaciones 06070902T01, 06070706P01, 06070706P02 y 06070706P01
            # no tienen el cod de abast a pesar de que si existen
            ca.of.setAttribute('cod_abastecimiento', 'DUMB')
        else:
            ca.copy('cod_abastecimiento', 'CodigoAb')

        # Codigo 06071901P01 estaba repetido
        ca.copy('cod_captacion', 'CodigoCap')
        ca.copy('sistema', 'Sistema')
        ca.copy('estado', 'Estado')
        ca.copy('volumen', 'Volumen')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('utm_z', 'z')
        
        ca.specificData()
        
        # Contiene la misma información que sistema
        #ca.copy('tipo_fuente', 'Tipo')
        
        newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "Error guardando la capa"
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"