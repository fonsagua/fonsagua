# -*- coding: utf-8 -*-

class CopyAttributesComunidades(CopyAttributes):

    def comunidadesSpecific(self):
        self.of.setAttribute('n_iglesias', self.iatts[self.ilayer.fieldNameIndex('nIglCat')] + self.iatts[self.ilayer.fieldNameIndex('nIglEva')])
        
    def doPatronatos(self):
        if self.iatts[self.ilayer.fieldNameIndex('HayPatront')] and self.iatts[self.ilayer.fieldNameIndex('HayPatront')] != 'No':
            adescos = [x for x in iface.legendInterface().layers() if x.name() == 'adescos'][0]
            adescos.dataProvider().clearErrors()
            adescosfields = adescos.dataProvider().fields()
            adescosfeat = QgsFeature()
            adescosfeat.setFields(adescosfields)
            adescosfeat.setAttribute('cod_comunidad', self.iatts[self.ilayer.fieldNameIndex('CodigoC')])
            adescosfeat.setAttribute('n_hombres', self.iatts[self.ilayer.fieldNameIndex('NumHomPt')])
            adescosfeat.setAttribute('n_mujeres', self.iatts[self.ilayer.fieldNameIndex('NumMujPt')])
            adescosfeat.setAttribute('presidencia', self.iatts[self.ilayer.fieldNameIndex('NombPresPt')])
            adescosfeat.setAttribute('antiguedad', self.iatts[self.ilayer.fieldNameIndex('AntigPt')])
            adescosfeat.setAttribute('anho_const', self.iatts[self.ilayer.fieldNameIndex('AnPersJur')])
            adescosfeat.setAttribute('legalizada', self.siNo2Chb(self.iatts[self.ilayer.fieldNameIndex('PatrPj')]))
            # ca.copy('', 'NCompPt') TRIGGER
            # ca.copy('', 'HayComApoy') Solo 1, eliminamos a conciencia

            (res, foo) = adescos.dataProvider().addFeatures([adescosfeat])
            if not res:
                print "Error adescos"
                print adescos.dataProvider().errors()
                return
    
    def doJuntasAgua(self):
        if self.iatts[self.ilayer.fieldNameIndex('hayJAgua')] and self.iatts[self.ilayer.fieldNameIndex('hayJAgua')] != 'No':
            ja = [x for x in iface.legendInterface().layers() if x.name() == 'juntas_agua'][0]
            ja.dataProvider().clearErrors()
            jafields = ja.dataProvider().fields()
            jafeat = QgsFeature()
            jafeat.setFields(jafields)
            
            relacion = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_relacion_comunidades_abastecimientos'][0]
            cod_abastecimiento = None
            for rfeat in relacion.getFeatures():
                if rfeat.attributes()[0] == self.iatts[self.ilayer.fieldNameIndex('CodigoC')]:
                    jafeat.setAttribute('cod_abastecimiento', rfeat.attributes()[2])
                    cod_abastecimiento = rfeat.attributes()[2]
                    break
                else:
                    continue
            
            if not cod_abastecimiento:
                print "La comunidad %s no tiene abastecimiento" % self.iatts[self.ilayer.fieldNameIndex('CodigoC')]
                return        
            abastecimiento = [x for x in iface.legendInterface().layers() if x.name() == 'abastecimientos'][0]
            abastecimientofeat = QgsFeature()
            abastecimiento.getFeatures( QgsFeatureRequest(QgsExpression('cod_abastecimiento = ' + cod_abastecimiento))).nextFeature( abastecimientofeat )
            
            abastecimientohonduras = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_abastecimiento'][0]
            abastecimientohondurasfeat = QgsFeature()
            abastecimientohonduras.getFeatures( QgsFeatureRequest(QgsExpression('CODIGOAB = ' + cod_abastecimiento))).nextFeature( abastecimientohondurasfeat )
            
            jafeat.setAttribute('ubicacion', self.iatts[self.ilayer.fieldNameIndex('CodigoC')])
            jafeat.setAttribute('per_juridica', self.siNo2Chb(self.iatts[self.ilayer.fieldNameIndex('JAguaPrsJ')]))
            jafeat.setAttribute('anho_per_juridica', self.iatts[self.ilayer.fieldNameIndex('EstJAPJ')])
            jafeat.setAttribute('antiguedad', self.iatts[self.ilayer.fieldNameIndex('AntJAgua')])
            jafeat.setAttribute('red_juntas', self.siNo2Chb(self.iatts[self.ilayer.fieldNameIndex('PertTedJA')]))
            jafeat.setAttribute('nom_red_juntas', self.iatts[self.ilayer.fieldNameIndex('QueRedJA')])
            if abastecimientohondurasfeat.attributes()[26].startswith('S'):
                jafeat.setAttribute('reglamento', 'true')
            # jafeat.setAttribute('', self.iatts[self.ilayer.fieldNameIndex('JAComApoy')]) Sin datos
            
            
            (res, foo) = ja.dataProvider().addFeatures([jafeat])
            if not res:
                print "Error juntas agua"
                print ja.dataProvider().errors()
                return
                
            
            bien = abastecimiento.dataProvider().changeAttributeValues({abastecimientofeat.id():{abastecimiento.fieldNameIndex('h_juntas_agua'):'true'}})
            if not bien:
                print "error"
                return


def myfunction():
    ilayer = [x for x in iface.legendInterface().layers() if x.name() == 'honduras_comunidades'][0]
    olayer = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0]
    olayer.dataProvider().clearErrors()
    caps = olayer.dataProvider().capabilities()

    ofields = olayer.dataProvider().fields()

    if not (caps & QgsVectorDataProvider.AddFeatures):
        print " ************** La capa no es editable, o algo parecido"
    newFeatures = []
    otrasorg = []
    for ifeat in ilayer.getFeatures():
        ca = CopyAttributesComunidades(ifeat, ofields, ilayer, 'CodigoC')
        
        ca.copy('comunidad', 'NombreC')
        ca.copy('cod_comunidad', 'CodigoC')
        ca.copy('cod_caserio', 'CodigoC')
        ca.copy('caserio', 'NombreC')
        ca.copy('fecha', 'Fecha', ca.toDate)
        ca.copy('punto_descripcion', 'Descrpt')
        ca.copy('departamento', 'Nomdep')
        ca.copy('cod_departamento', 'Nomdep')
        ca.copy('municipio', 'Nommunic')
        ca.copy('cod_municipio', 'Codmunic')
        ca.copy('canton', 'nomaldea')
        ca.copy('cod_canton', 'CodAldea')
        ca.copy('cuenca', 'Cuenca')
        ca.copy('n_familias', 'NumFam')
        ca.copy('n_habitantes', 'NumHab')
        ca.copy('n_viviendas', 'NumViv')
        ca.copy('anho_establecimiento', 'NaciCom', ca.v2int)
        ca.copy('tip_nucleo', 'TipoNuc')
        # TODO: Aclarar que en 2 tambien existe n_hom_jovenes y n_muj_jovenes (5-18 anhos)
        # pero asignamos todos al sector 18-60
        ca.copy('n_hombres', 'NumHombres')
        ca.copy('n_mujeres', 'NumMujeres')
        ca.copy('n_ninhos', 'NumNinhos')
        ca.copy('n_ninhas', 'NumNinhas')
        ca.copy('n_ancianos', 'NumAnciano')
        ca.copy('n_ancianas', 'NumAnciana')
        ca.copy('tip_origen', 'TipoPOrige')
        ca.copy('antiguedad', 'AntigDesp')
        
        ca.copy('h_adescos', 'HayPatront', ca.siNo2Chb)
        
        if ifeat.attributes()[ilayer.fieldNameIndex('HaySPFam')].startswith('S'):
            otrasorg.append({'cod_comunidad':ca.pkvalue, 'tipo_organizacion':u'Asociaci\xf3n de padres y madres de familia'})
        
        if ifeat.attributes()[ilayer.fieldNameIndex('NomCoopP')]:
            otrasorg.append({'cod_comunidad':ca.pkvalue, 'nombre':ifeat.attributes()[ilayer.fieldNameIndex('NomCoopP')], 'tipo_organizacion':'Cooperativa de producci\xf3n', 'actividad':ifeat.attributes()[ilayer.fieldNameIndex('ActivPrinc')]})
        
        if ifeat.attributes()[ilayer.fieldNameIndex('BankComuna')].startswith('S'):
            otrasorg.append({'cod_comunidad':ca.pkvalue, 'nombre':'Banco comunal', 'tipo_organizacion':'Caja rural', 'actividad':''})

        ca.copy('exp_ongs', 'ExpONGs', ca.siNo2Chb)
        
        ca.copy('h_cargos_publicos', 'PersCPub', ca.siNo2Chb)
        
         # Trigger. Comprobar que da bien.
        # ca.copy('f_primario', 'FamAgropec')
        # ca.copy('f_secundario', 'FamIndust')
        # ca.copy('f_terciario', 'FamServ')
        # ca.copy('f_c_propia', 'FamCuenPro')
        
        ca.copy('cp_granos', 'GranosBasi', ca.v2int)
        ca.copy('sub_granos', 'GranBasSub', ca.siNo2Chb)
        ca.copy('cp_ganaderia', 'Ganad')
        ca.copy('sub_ganaderia', 'GanadSubs', ca.siNo2Chb)
        ca.copy('cp_otros', 'CPOtros')
        ca.copy('sub_otros', 'CPOtrSubs', ca.siNo2Chb)
        
        # Trigger. Comprobar que da bien.
        # ca.copy('f_c_ajena', 'NFamCuenAj')
        
        ca.copy('ca_cafe', 'CortaCafe')
        ca.copy('ca_canha', 'CortaCanha')
        ca.copy('ca_otros', 'CAjOtros')
        
        # TODO: Tiene datos
        # ca.copy('', 'CAjOtroSub')
        
        ca.copy('tip_cultivos', 'CmTipoCult')
        ca.copy('f_propietarias', 'NFamPropie')
        ca.copy('prop_hombres', 'NPropHomb')
        ca.copy('prop_mujeres', 'NPropMuj')
        ca.copy('prop_area_cultivada', 'ACultivFam')
        ca.copy('f_arrendatarias', 'NFamArrend')
        ca.copy('arre_hombres', 'NArrCabH')
        ca.copy('arre_mujeres', 'NArrCabM')
        ca.copy('arre_area_cultivada', 'ACulFArren')
        ca.copy('f_medias', 'FamAMedias')
        ca.copy('med_hombres', 'FamAMedH')
        ca.copy('med_area_cultivada', 'ACulFColon')
        ca.copy('sup_total_riego', 'SupRegTot')
        ca.copy('tip_regadio', 'TipoRegad')
        
        # Trigger. Comprobar que da bien.
        # ca.copy('produccion', 'SacosFam')
        # ca.copy('consumo', 'SacFConsPr')
        ca.copy('h_cooperativas', 'ExistCoopP', ca.siNo2Chb)
        ca.copy('f_comercio', 'NumFamCome')
        ca.copy('comer_serv', 'HayComerc', ca.siNo2Chb)
        ca.copy('f_industria', 'NumFamIndu')
        ca.copy('f_construccion', 'NumFConstr')
        ca.copy('f_maquila', 'NumFamMaq')
        ca.copy('f_otros_sec', 'NumFamOtro')
        ca.copy('ind_tall_maq', 'HayIndustr', ca.siNo2Chb)
        ca.copy('electricidad', 'HayElectr', ca.siNo2Chb)
        ca.copy('viv_electricidad', 'NumVivElec')
        # TODO: Son lempiras/mes, cambiar etiqueta
        ca.copy('tarifa_electricidad', 'Cuota')
        ca.copy('telf_fijo', 'HayTelfijo', ca.siNo2Chb)
        # Avisar del cambio bueno/malo/regular
        ca.copy('acceso_ver', 'CarrtVeran', lambda v: 'Transitable' if v in ['Bueno', 'Regular'] else 'Intrasitable')
        ca.copy('acceso_inv', 'CarrtInv')
        ca.copy('trans_publico', 'TransPub', ca.siNo2Chb)
        
        # TODO: igual hay que hacerlos a mano
        # ca.copy('', 'FrqTrPub')
        # ca.copy('', 'DistAParad')
        # ca.copy('', 'TCabMunPie')
        # ca.copy('', 'TCabMunVh')
        ca.copy('f_viv_prop', 'FPropViv')
        ca.copy('f_viv_noprop', 'FNoPrpTViv')
        ca.copy('viv_prop_mujeres', 'MujProp')
        ca.copy('viv_prop_hombres', 'HomProp')
        
        # TODO. Falta. Sera ¿viv_mixto?
        # ca.copy('', 'FamVivLadr')
        
        ca.copy('viv_bahareque', 'FamVivBarq')
        ca.copy('viv_adobe', 'FamVivAdob')
        ca.copy('viv_otros', 'FamVivOtro')
        ca.copy('f_dat_abast', 'FuentDatos')
        ca.copy('resum_abast', 'ResAbAgua')
        ca.copy('coment_ser', 'ObsServ')
        ca.copy('coment_eco', 'ComAEco')
        ca.copy('resum_econ', 'ResAEco')
        ca.copy('coment_org', 'ObsAspOrg')
        ca.copy('coment_agua', 'ComInfraAg')
        ca.copy('coment_pob', 'ComDatPob')
        ca.copy('resum_censo', 'ResDatPob')
        ca.copy('utm_z', 'AlturaC', ca.toZ)
        ca.copy('coment_gen', 'ComMunic')

        # Trigger. Comprobar que da bien.
        # ca.copy('tot_adultos', 'TotalAdult')
        # ca.copy('tot_ninhos', 'TotalNin')
        # ca.copy('tot_ancianos', 'TotalAncia')
        
        ca.copy('prop_registrada', 'LegalReg')
        ca.copy('prop_noregistrada', 'LegalNoReg')
        ca.copy('prop_tramites', 'LegalTram')
        
        
        
        ca.copy('f_otros_ter', 'NumFamCOtr')
        
        # TODO a mano en centros actividad economica
        # ca.copy('', 'TipoComerc')
        # ca.copy('', 'TipoIndust')
        
        ca.copy('necesidad1', 'Necesidad1')
        ca.copy('necesidad2', 'Necesidad2')
        ca.copy('necesidad3', 'Necesidad3')
        ca.copy('necesidad4', 'Necesidad4')
        ca.copy('enf_tip_adultos', 'EnfComunes')
        ca.copy('enf_tip_ninhos', 'EnfComunes')

        # TODO: Relacionados con Sanidad
        # ca.copy('', 'tCenSalud')
        # ca.copy('', 'TCESAMO')
        # ca.copy('', 'frqCESAMO')
        # ca.copy('', 'VisPromSal')
        # ca.copy('', 'frqVisProm')
        # ca.copy('', 'FnteInfoSa')
        # ca.copy('f_dat_sanidad', 'FntelnfEtd')
        # ca.copy('', 'TipAsisSan')
        
        ca.copy('sist_abastecimiento', 'HayAbast', lambda v: None if not v else 'Si' if v.startswith('S') else 'No' if v.startswith('N') else 'Parcial')
        ca.copy('n_viv_abast', 'ParcViv')
        
        # TODO. Igual hay que cambiar cosas
        # ca.copy('', 'OrigAguaS')
        ca.copy('n_vacas', 'Vacas')
        ca.copy('n_cerdos', 'Cerdos')
        ca.copy('n_gallinas', 'Gallinas')
        ca.copy('n_caballos', 'Caballos')
        ca.copy('n_burros', 'Burros')
        ca.copy('agroquimicos', 'AgroQuim', ca.siNo2Chb)
        ca.copy('tip_agroquimicos', 'TipoAgroQ')
        ca.copy('tec_conservacion', 'ConsrvSuel', ca.siNo2Chb)
        ca.copy('tip_tec_conservacion', 'TipoCsrvS')
        ca.copy('form_agricola', 'FormacAgro', ca.siNo2Chb)
        ca.copy('f_form_agricola', 'NFamForAgr')
        ca.copy('form_ambiental', 'FormAmb', ca.siNo2Chb)
        ca.copy('f_form_ambiental', 'NFamFormAm')
        ca.copy('deforestacion', 'Deforest')
        ca.copy('avance_fagricola', 'AvanFrAgr', lambda v: v.replace('o', 'a'))
        # TODO. los cuatros siguientes habria que revisarlos
        ca.copy('veg_residencial', 'UsoSuelo', lambda v: 'true' if v.startswith('Residencial') else 'false')
        ca.copy('veg_industrial', 'UsoSuelo', lambda v: 'true' if v == 'Industrial' else 'false')
        ca.copy('veg_agricola', 'UsoSuelo', lambda v: 'true' if v.startswith('Agr') else 'false')
        ca.copy('veg_forestal', 'UsoSuelo', lambda v: 'true' if v == 'Forestal' else 'false')
        
        # TODO: No hay equivalente
        # ca.copy('', 'ProfSuelo')
        ca.copy('riesgo_erosion', 'RiesgEros')
        # TODO: No hay equivalente
        # ca.copy('', 'TextSuelo')
        ca.copy('frec_incendios', 'FreqIncend')
        ca.copy('est_hidrogeologicos', 'HayHidrog', ca.siNo2Chb)
        ca.copy('autor_hidrogeo', 'HidrgAut')
        ca.copy('pozos_extraccion', 'NivPozos', ca.isTrue)
        ca.copy('pozos_nivel', 'NivPozos')
        
        # TODO
        # ca.copy('', 'CobVegMnt')
        # ca.copy('', 'TipoVgMic')
        ca.copy('conserv_ma', 'HayConsvMA', ca.siNo2Chb)
        ca.copy('desc_conserv_ma', 'DescConsMA')
        
        # TODO
        # ca.copy('', 'Especies')
        # ca.copy('', 'DesfAIMcr')
        ca.copy('llano', 'SiLlano')
        ca.copy('pend_media', 'SiMedia')
        ca.copy('pend_elevada', 'Sielevada')
        
        # TODO
        # ca.copy('', 'Coniferas')
        # ca.copy('', 'Latifol')
        # ca.copy('', 'Mixto')
        # ca.copy('', 'Cultivos')
        # ca.copy('', 'Mangle')
        # ca.copy('', 'Matorral')
        # ca.copy('', 'Pasto')
        # ca.copy('', 'Otros')
        ca.copy('coc_lenha', 'Fogon')
        ca.copy('coc_lenha_mej', 'Estufa')
        
        # Trigger. Comprobar que da bien.
        # ca.copy('', 'x')
        # ca.copy('', 'y')

        ca.copy('med_hombres', 'FamAMedM')
        ca.copy('fr_inundac', 'Principal', lambda v: 'true' if v and v.startswith('Inund') else 'false')
        ca.copy('fr_desbord', 'Principal', lambda v: 'true' if v and v.startswith('Desbor') else 'false')
        ca.copy('fr_otros', 'Principal', lambda v: 'true' if v and v.startswith('Otros') else 'false')
        ca.copy('f_dat_frg', 'FuenteDat')
        ca.copy('ult_fenomeno', 'UltimoF')
        ca.copy('comite_riesgos', 'ComiteR', ca.siNo2Chb)
        ca.copy('capac_riesgos', 'Capacit', ca.siNo2Chb)
        ca.copy('fr_otros_detalle', 'OtrosFR')
        ca.copy('coment_frg', 'ComentFR')
        
        # TODO
        # ca.copy('', 'HayEvacAGr')
        # ca.copy('', 'TipoEvacAg')
        # ca.copy('', 'TratAgGris')
        # ca.copy('', 'DisposicEx')
        # ca.copy('', 'ExistLetr')
        ca.copy('let_hoyo', 'NumFosSimp')
        ca.copy('h_let_abonera', 'NumAboner', lambda v: 'true' if int(v) > 0 else 'false')
        ca.copy('let_abonera', 'NumAboner')
        ca.copy('let_hidra', 'NumCierrHi')
        ca.copy('let_septica', 'NumFosSept')
        ca.copy('coment_san', 'ComentSan')
        ca.copy('h_lavad_comun', 'HayLavC', ca.siNo2Chb)
        ca.copy('tip_almacenamiento', 'TipoAlmA')
        ca.copy('n_lavaderos', 'NLavad')
        ca.copy('n_pilas', 'npilas')
        
        # TODO ca.copy('', 'haytratagr')
        ca.copy('n_barriles', 'nbarr')
        # Trigger. Comprobar que da bien.
        ca.copy('cons_hab', 'ConsumHab')
        ca.copy('t_medio_fuente', 'TMedioFu')
        ca.copy('seca_suficiencia', 'Suficiente', ca.siNo2Chb)
        ca.copy('seca_t_espera', 'TEspCola')
        ca.copy('lluvia_suficiencia', 'LlSufic')
        ca.copy('lluvia_t_espera', 'LlTespCola')
        ca.copy('f_compra', 'CosAguaExt', lambda v: 'true' if v else 'false')
        ca.copy('coste_agua', 'CosAguaExt')
        ca.copy('coment_hab', 'ComHabita')
        ca.copy('res_hab_agua', 'resHabitos')
        # Trigger. Comprobar que da bien.
        # ca.copy('tot_s_abast', 'totcom') tot_ll_abast
        # ca.copy('tot_s_sin_abast', 'totnocom') tot_ll_sin_abast
        ca.copy('disp_basuras', 'DispBasur')
        ca.copy('cent_educativos', 'hayEsc', ca.siNo2Chb)
        ca.copy('trat_aboneras', 'tratAboner')
        ca.copy('disp_abono', 'dispFinAb')
        ca.copy('uso_letrinas', 'usoLet', ca.siNo2Chb)
        ca.copy('pq_no_aboneras', 'pQNoUso')
        
        
        # TODO Aclarar que se distingue entre seca y lluvia y se pone en los dos lo mismo
        ca.copy('aba_s_domiciliar', 'abastdom')
        ca.copy('aba_ll_domiciliar', 'abastdom')
        ca.copy('aba_s_cantareras', 'llavepub')
        ca.copy('aba_ll_cantareras', 'llavepub')
        ca.copy('aba_s_broquel_com', 'pozcomun')
        ca.copy('aba_ll_broquel_com', 'pozcomun')
        ca.copy('aba_s_broquel', 'pozpart')
        ca.copy('aba_ll_broquel', 'pozpart')
        ca.copy('aba_s_nacimiento', 'vanmanant')
        ca.copy('aba_ll_nacimiento', 'vanmanant')
        ca.copy('aba_s_rio', 'vanrio')
        ca.copy('aba_ll_rio', 'vanrio')
        ca.copy('aba_s_quebrada', 'vanquebr')
        ca.copy('aba_ll_quebrada', 'vanquebr')
        ca.copy('aba_s_compra', 'compragua')
        ca.copy('aba_ll_compra', 'compragua')
        ca.copy('lug_agua_cercano', 'lugarprox')
        
        ca.comunidadesSpecific()
        (res, foo) = olayer.dataProvider().addFeatures([ca.getNewFeature()])
        if not res:
            print "************** Error guardando la capa ********* "
            print olayer.dataProvider().errors()
            return
        ca.doPatronatos()
        ca.doJuntasAgua()


    otrasorglayer = [x for x in iface.legendInterface().layers() if x.name() == 'otras_organizaciones'][0]
    otrasorglayer.dataProvider().clearErrors()
    otrasorgfields = otrasorglayer.dataProvider().fields()
    otrasorgfeats = []
    for org in otrasorg:
        otrasorgfeat = QgsFeature()
        otrasorgfeat.setFields(otrasorgfields)
        for k,v in org.items():
            otrasorgfeat.setAttribute(k, v)
        otrasorgfeats.append(otrasorgfeat)
        
    (res, foo) = otrasorglayer.dataProvider().addFeatures(otrasorgfeats)

    if not res:
        print "Error otras organizaciones"
        print otrasorglayer.dataProvider().errors()
        return
    
    

myfunction()