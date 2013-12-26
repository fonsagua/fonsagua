# -*- coding: utf-8 -*-

from PyQt4.QtCore import QPyNullVariant

class CopyAttributes():
    def __init__(self, ifeat, ofields, ilayer):
        self.ilayer = ilayer
        self.iatts = ifeat.attributes()
        self.of = QgsFeature()
        self.of.setFields(ofields)
        a = ifeat.geometry().convertToMultiType()
        if not a:
            print "error multitype"
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
        tipologia = self.iatts[self.ilayer.fieldNameIndex('Tipo')]
        if (tipologia.startswith('Adu')):
            self.of.setAttribute('tipologia_tuberia', 'Aducci' + unichr(243) + 'n')
        else:
            if (tipologia.startswith('Impul')):
                self.of.setAttribute('tipologia_tuberia', 'Impulsi' + unichr(243) + 'n')
            else:
                if (tipologia.startswith('Distri')):
                    self.of.setAttribute('tipologia_tuberia', 'Distribuci' + unichr(243) + 'n')

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_tuberias'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'tuberias'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('cod_abastecimiento', 'CodigoAb')
            ca.copy('diametro', 'Diametro')
            ca.copy('estado', 'Estado')
            ca.copy('material', 'Material')
            ca.copy('cod_tuberia', 'CodigoTub')
            ca.copy('fugas', 'Fugas', ca.siNo2Chb)
            ca.copy('loc_fugas', 'FugLoc', lambda v: v if v else None)
            
            
            
            ca.specificData()
            
            # TODO
            #of.setAttribute('', iatts[ilayer.fieldNameIndex('CodigoCap')])
            
            newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "************** Error guardando la capa ********* "
        print olayer.dataProvider().errors()
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"