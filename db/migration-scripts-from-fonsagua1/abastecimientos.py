# -*- coding: utf-8 -*-

class CopyAttributesAbastecimientos(CopyAttributes):
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


class Abastecimientos(LayerMigration):
    def execute(self):
        newFeatures = []
        for ifeat in self.ilayer.getFeatures():
            ca = CopyAttributesAbastecimientos(ifeat, self.ofields, self.ilayer)
            
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
            ca.copy('frec_con_calidad', 'freqContro')
            ca.copy('coment_desinfeccion', 'COMCLORO')
            ca.copy('coment_sis', 'COMABAST')
            ca.copy('gestion', 'GESTOR')
           
            ca.copy('mant_tecnicos', 'HAYTECMAN', ca.siNo2Chb)
            ca.copy('coment_tarifa', 'FUNCSISCOB')
            ca.copy('gastos_cubiertos', 'COMGASTT')
            ca.copy('coment_gestion', 'COMGEST')
            ca.copy('frec_desinfeccion', 'FREQDESINF')
            ca.copy('con_funcionamiento', 'CONTRFUNC', ca.siNo2Chb)
            ca.copy('fre_con_funcionamiento', 'FCONTRFNC')
            ca.copy('ong', 'GESTORONG')
            ca.copy('frec_pago', 'FREQPAGO')
            
            ca.copy('tarifa_agua',  'HAYCUOTAF', ca.siNo2Chb)
            ca.copy('cuota_domiciliar', 'CUOTAF')
            ca.copy('tarifa_variable',  'HAYCUOTAV', ca.siNo2Chb)
            ca.copy('cuota_variable', 'CUOTAV')
            
            ca.specificData()
            
            # TODO pone en todas lo mismo asi que se obvia GRADIMPCOM(iria en implicacion_comunidad), CUOTAOPMAN, COMODAGUA
            newFeatures.append(ca.getNewFeature())

        dumb = QgsFeature()
        dumb.setFields(self.ofields)
        dumb.setAttribute('cod_abastecimiento', 'DUMB')
        newFeatures.append(dumb)

        self._saveLayer(newFeatures)
        

abastecimientos = Abastecimientos('honduras_abastecimiento', 'abastecimientos')
abastecimientos.execute()    

