# -*- coding: utf-8 -*-

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
        if ilayer.hasGeometryType():
            self.of.setGeometry(ifeat.geometry())

        self.codigoscomunidad = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0].dataProvider().uniqueValues(1)
        
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
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_relacion_comunidades_abastecimientos'][0]
    olayer  = [x for x in iface.legendInterface().layers() if x.name() == 'r_abastecimientos_comunidades'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    codigoscomunidad = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0].dataProvider().uniqueValues(1)
     
    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    auxCodes = {}
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_comunidad', 'codigoc')
        ca.copy('cod_abastecimiento', 'codigoab')
        
        codcom = ifeat.attributes()[0]
        auxCodes[codcom] = True
        if codcom not in codigoscomunidad:
            print 'El codigo no es valido %s' % (codcom)
            return
        
        # print ca.getNewFeature().attributes()
        newFeatures.append(ca.getNewFeature())

    (res, foo) = olayer.dataProvider().addFeatures(newFeatures)
    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
        return

    # updateComunidades(auxCodes)
    
def updateComunidades(auxCodes):
    comunidades = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0]
    comunidades.dataProvider().clearErrors()
    comunidadesFields = comunidades.dataProvider().fields()
    comunidadesIdAtts = {}
    for feature in comunidades.getFeatures():
        idx = comunidades.fieldNameIndex('cod_comunidad')
        if feature.attributes()[idx] in auxCodes:
            comunidadesIdAtts[feature.id()] = {comunidades.fieldNameIndex('h_cooperativas'):'true'}
    r = comunidades.dataProvider().changeAttributeValues(comunidadesIdAtts)
    if not r:
        print 'Error'


myfunction()