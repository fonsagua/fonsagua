# -*- coding: utf-8 -*-
import processing

from PyQt4.QtCore import QPyNullVariant

##
## RECUERDA PONER EL ENCODING DE LA CAPA ORIGINAL EN WINDOWS-1250 EN LA CAPA ORIGINAL
##
class CopyAttributes():
    def __init__(self, ifeat, ofields, ilayer):
        self.ilayer = ilayer
        self.iatts = ifeat.attributes()
        self.of = QgsFeature()
        self.of.setFields(ofields)
        self.of.setGeometry(ifeat.geometry())

        self.codigoscomunidad = processing.getobject("comunidades").dataProvider().uniqueValues(1)

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
        
    def toCodigoC(self, v):
       '''
       takes 8 characters from v and use it as codigoc if that value is valid codigoc
       if not COMUNIDAD_FALSA is used as codigoc
       '''
       codigoc = v[0:8]
       if codigoc in self.codigoscomunidad:
           return codigoc
       return 'COMUNIDAD_FALSA'
       
    def getNewFeature(self):
        return self.of

    def comunidadesSpecific(self):
        self.of.setAttribute('n_iglesias', self.iatts[self.ilayer.fieldNameIndex('nIglCat')] + self.iatts[self.ilayer.fieldNameIndex('nIglEva')])


def myfunction():
    ilayer = processing.getobject("honduras_ccomunit")
    olayer = processing.getobject("otros_servicios")
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    
    for ifeat in ilayer.getFeatures():
        # Si no tiene el codigo de comunidad, es un dato erroneo
        if not ifeat.attributes()[1]:
            continue
            
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('nombre', 'Nombre')
        ca.copy('cod_servicio', 'id_ccomun')
        ca.copy('cod_comunidad', 'id_ccomun', ca.toCodigoC)
        ca.copy('tipo_servicio', 'Tipo', lambda v: 'Otros' if not v else 'Iglesia' if v.startswith('Igle') else u'Instalaci\xf3n deportiva' if v.startswith('Insta') else 'Centro comunitario' if v.startswith('Casa') else 'Otros')
        ca.copy('utm_z', 'z')
        ca.copy('utm_x', 'x')
        ca.copy('utm_y', 'y')
        
        
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    res = True
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return
        
myfunction()