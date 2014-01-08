# -*- coding: utf-8 -*-
class CopyAttributesCEducativos(CopyAttributes):

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

ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_ceducativo'][0]
olayer = [x for x in iface.legendInterface().layers() if x.name() == 'centros_educativos'][0]
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesCEducativos(ifeat, ofields, ilayer)
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