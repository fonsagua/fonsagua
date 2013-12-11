# -*- coding: utf-8 -*-
import processing

from PyQt4.QtCore import QPyNullVariant

# select substr(id_ptorepr, 0, 9), id_ptorepr, codigoc from puntorepr where codigoc IS NOT NULL AND substr(id_ptorepr, 0, 9) = codigoc;
# select count(*) from puntorepr where codigoc IS NOT NULL;  =149
# select count(*) from puntorepr where codigoc IS NOT NULL AND substr(id_ptorepr, 0, 9) = codigoc; = 141
# Pero en los casos que falla, es porque se ha puesto codigoc mas grande de lo que deberia ser, por tanto es 
# correcto crear codigoc a partir de id_ptorepr
# Hay id_ptorepr duplicados, pero los duplicados con datos malos tienen todos codigoc = NULL

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
         
    def siNo2Chb(self, v):
        '''
        str should be 'Si/No/Null) and returns 'true/false'
        uses startswith to avoid encoding problems
        '''
        if v and v.startswith('S'):
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
    ilayer = processing.getobject("honduras_puntorepr")
    olayer = processing.getobject("puntos_viviendas")
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()
    duplicatedCodigoC = []
    existent = []
    for ifeat in ilayer.getFeatures():
        cod = ifeat.attributes()[6]
        if  cod in existent:
            duplicatedCodigoC.append(cod)
        else:
            existent.append(cod)
    del existent
    del ifeat
    
    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    
    for ifeat in ilayer.getFeatures():
        if ifeat.attributes()[6] in duplicatedCodigoC:
            if not ifeat.attributes()[0]:
                continue
        ca = CopyAttributes(ifeat, ofields, ilayer)
        ca.copy('cod_vivienda', 'id_ptorepr')
        ca.copy('cod_comunidad', 'id_ptorepr', ca.toCodigoC)
        ca.copy('tipo', 'Tipo', lambda v: 'Casa mas alta' if v=='Alto' else 'Casa mas baja' if v=='Bajo' else 'Otras casas' )
        ca.copy('utm_z', 'Altitud')
        ca.copy('descripcion', 'Descripc')
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