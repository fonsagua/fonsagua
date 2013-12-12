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
    
    def specificData(self):
        tipomant = self.iatts[self.ilayer.fieldNameIndex('TIPOMANT')]
        if (tipomant.lower() == 'correctivo'):
            self.of.setAttribute('tipo_mantenimiento', 'Correctivo')
        else:
            if (tipomant.lower() == 'preventivo'):
                self.of.setAttribute('tipo_mantenimiento', 'Preventivo')
            else:
                self.of.setAttribute('tipo_mantenimiento', '')
        
        dondedesin = self.iatts[self.ilayer.fieldNameIndex('DONDEDESIN')]
        if (dondedesin == 'Tanque distribucin'):
            self.of.setAttribute('des_tanque', True)
        else:
            if (dondedesin == 'Impelencia'):
                self.of.setAttribute('des_impelencia', True)
            else:
                self.of.setAttribute('des_otra', True)

ilayer = processing.getobject("abastecimiento")
olayer = processing.getobject("abastecimientos")
olayer.dataProvider().clearErrors()
caps = olayer.dataProvider().capabilities()

ofields = olayer.dataProvider().fields()

if caps & QgsVectorDataProvider.AddFeatures:
    newFeatures = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributes(ifeat, ofields, ilayer)
        
        ca.copy('cod_abastecimiento', 'CODIGOAB')
        ca.copy('tot_consumo', 'CONSTOTAL')
        ca.copy('cons_domestico', 'CONSDOM')
        ca.copy('anho_construccion', 'ANNOCONST')
        ca.copy('tipo_sistema', 'SISTCONST')
        ca.copy('ent_constructora', 'QUIENCONST')
        ca.copy('des_agua', 'HAYDESINF', ca.siNo2Chb)
        ca.copy('metodo', 'METDESINF')
        ca.copy('coste_desinfeccion', 'COSTDESINF')
        ca.copy('tarifa_agua', 'SEPAGAAG', ca.siNo2Chb)
        ca.copy('coste_mantenimiento', 'COSTEMANT')
        ca.copy('con_calidad', 'CONTRCALID', ca.siNo2Chb)
        ca.copy('frec_con_calidad', 'CODIGOAB')
        ca.copy('coment_desinfeccion', 'COMCLORO')
        ca.copy('coment_sis', 'COMABAST')
        ca.copy('gestion', 'GESTOR')
        ca.copy('h_juntas_agua', 'HAYJAGUA', ca.siNo2Chb)
        ca.copy('mant_tecnicos', 'HAYTECMAN', ca.siNo2Chb)
        ca.copy('coment_tarifa', 'FUNCSISCOB')
        ca.copy('gastos_cubiertos', 'COMGASTT')
        ca.copy('coment_gestion', 'COMGEST')
        ca.copy('frec_desinfeccion', 'FREQDESINF')
        ca.copy('con_funcionamiento', 'CONTRFUNC', ca.siNo2Chb)
        ca.copy('fre_con_funcionamiento', 'FCONTRFNC')
        ca.copy('ong', 'GESTORONG')
        ca.copy('frec_pago', 'FREQPAGO')
        
        
        # TODO Ahora tenemos distintos tipos de cuotas
        ca.copy('cuota_domiciliar', 'CUOTAAGUA')
        ca.copy('cuota_comercial', 'CUOTAAGUA')
        ca.copy('cuota_cantarera', 'CUOTAAGUA')
        ca.copy('cuota_otros', 'CUOTAAGUA')
        
        ca.specificData()
        
        """ TODO
        of.setAttribute('', iatts[ilayer.fieldNameIndex('NLLAVESPUB')])
        of.setAttribute('implicacion_comunidad', iatts[ilayer.fieldNameIndex('GRADIMPCOM')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('VALOR')])
        of.setAttribute('valoracion_sistema', iatts[ilayer.fieldNameIndex('VALCONSUM')])
        
        of.setAttribute('', iatts[ilayer.fieldNameIndex('CANTSUF')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('AGUACONT')])
        # Ahora tenemos distintos tipos de cuotas
        of.setAttribute('', iatts[ilayer.fieldNameIndex('CUOTAAGUA')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('CUOTAOPMAN')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('HAYREGLAM')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('HAYCUOTAF')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('CUOTAF')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('HAYCUOTAV')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('CUOTAV')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('DISTLLPUB')])
        
        of.setAttribute('', iatts[ilayer.fieldNameIndex('COMODAGUA')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('NUMMUJ')])
        of.setAttribute('', iatts[ilayer.fieldNameIndex('HAYDIBOM')])"""
        
        newFeatures.append(ca.getNewFeature())

    dumb = QgsFeature()
    dumb.setFields(ofields)
    dumb.setAttribute('cod_abastecimiento', 'DUMB')
    newFeatures.append(dumb)

    (res, outFeats) = olayer.dataProvider().addFeatures( newFeatures )

    if not res:
        print "Error guardando la capa"
    else:
        print "Tiene pinta de estar bien"

else:
    print "La capa no es editable, o algo parecido"