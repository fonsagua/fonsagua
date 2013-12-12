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
        energia = self.iatts[self.ilayer.fieldNameIndex('Energia')]
        if (energia == 'Motor diesel'):
            self.of.setAttribute('energia', 'Motor Di' + unichr(233) + 'sel')
        else:
            if (energia == 'Red elctrica'):
                self.of.setAttribute('energia', 'Red El' + unichr(233) + 'ctrica')
            else:
                self.of.setAttribute('energia', energia)

ilayer = processing.getobject("bombas")
olayer = processing.getobject("bombeos")
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        if (not ca.validRow()):
            ca.of.setAttribute('cod_abastecimiento', 'DUMB')
        else:
            ca.copy('cod_abastecimiento', 'CodigoAb')
            
        ca.copy('cod_bombeo', 'CodigoB')
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
        print "Error guardando la capa"
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"