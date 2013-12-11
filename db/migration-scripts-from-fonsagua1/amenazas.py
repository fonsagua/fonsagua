# -*- coding: utf-8 -*-
import processing

from PyQt4.QtCore import QPyNullVariant

##
## RECUERDA PONER EL ENCODING DE LA CAPA ORIGINAL EN WINDOWS-1250 EN LA CAPA ORIGINAL
##

# 06073901AM01 -> n_fam_afectadas, puesto a mano: 41
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
         
    def siNo2Chb(self, strvalue):
        '''
        str should be 'Si/No/Null) and returns 'true/false'
        uses startswith to avoid encoding problems
        '''
        if strvalue and strvalue.startswith('S'):
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

    def comunidadesSpecific(self):
        self.of.setAttribute('n_iglesias', self.iatts[self.ilayer.fieldNameIndex('nIglCat')] + self.iatts[self.ilayer.fieldNameIndex('nIglEva')])


def myfunction():
    ilayer = processing.getobject("honduras_amenazas")
    olayer = processing.getobject("amenazas")
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_amenaza', 'CodigoFR')
        ca.copy('tipo_amenaza', 'TipoFR', lambda v: 'Deslizamientos' if v.startswith('Desliza') else u'Desbordamiento de r\xedo' if v.startswith('Desbor') else 'Inundaciones' if v.startswith('Inun') else 'Puntos de asalto' if v.startswith('Inse') else 'Otros')
        ca.copy('n_fam_afectadas', 'FamiliasAf')
        ca.copy('utm_z', 'Altura')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        ca.copy('cod_comunidad', 'codigoc')
        
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    res = True
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return


myfunction()