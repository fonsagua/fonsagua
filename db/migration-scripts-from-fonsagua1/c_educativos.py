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
        return (self.iatts[self.ilayer.fieldNameIndex('CodigoC')] != None)
    
    def specificData(self):
        
        ninhos = self.iatts[self.ilayer.fieldNameIndex('AlumNinhos')]
        if (self.iatts[self.ilayer.fieldNameIndex('PBNinhos')] > 0):
            ninhos += self.iatts[self.ilayer.fieldNameIndex('PBNinhos')]
        self.of.setAttribute('n_ninhos', ninhos)
        
        ninhas = self.iatts[self.ilayer.fieldNameIndex('AlumNinhas')]
        if (self.iatts[self.ilayer.fieldNameIndex('PBNinhas')] > 0):
            ninhas += self.iatts[self.ilayer.fieldNameIndex('PBNinhas')]
        self.of.setAttribute('n_ninhas', ninhas)

ilayer = processing.getobject("ceducativo")
olayer = processing.getobject("centros_educativos")
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        if (ca.validRow()):
            ca.copy('nombre', 'Nombre')
            ca.copy('cod_comunidad', 'CodigoC')
            # NumAulas Decide eliminarse por no ser importante
            # ExistePreB, PBNinhos, PBNinhas
            ca.copy('cod_c_educativo', 'id_cedu')
            ca.copy('tot_alumnos', 'AlumnosTot')
            ca.copy('n_profesores', 'ProfesoTot')
            ca.copy('n_prof_com', 'ProfesCom')
            ca.copy('prog_esc_salud', 'EscSalud', ca.siNo2Chb)
            ca.copy('frec_prog', 'FrecEscSal')
            ca.copy('comentarios', 'ComEsc')
            ca.copy('programa', 'cualescsal')
            ca.copy('utm_x', 'x')
            ca.copy('utm_y', 'y')
            ca.copy('utm_z', 'z')
            
            ca.specificData()
            
            # TODO
            # Aclarar la traduccion de grados (con numeros) a los niveles descriptivos
            #ca.copy('niveles', 'GradosExis')
            
            newFeatures.append(ca.getNewFeature())

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "Error guardando la capa"
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"