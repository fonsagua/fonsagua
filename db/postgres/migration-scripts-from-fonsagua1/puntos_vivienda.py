# -*- coding: utf-8 -*-

class PuntosVivienda(LayerMigration):
    def execute(self):
        duplicatedCodigoC = []
        existent = []
        for ifeat in self.ilayer.getFeatures():
            cod = ifeat.attributes()[6]
            if  cod in existent:
                duplicatedCodigoC.append(cod)
            else:
                existent.append(cod)
        del existent
        del ifeat
        
        newFeatures = []
        
        for ifeat in self.ilayer.getFeatures():
            if ifeat.attributes()[6] in duplicatedCodigoC:
                if not ifeat.attributes()[0]:
                    continue
            ca = CopyAttributes(ifeat, self.ofields, self.ilayer)
            ca.copy('cod_vivienda', 'id_ptorepr')
            ca.copy('cod_comunidad', 'id_ptorepr', ca.toCodigoC)
            ca.copy('tipo', 'Tipo', lambda v: 'Casa mas alta' if v=='Alto' else 'Casa mas baja' if v=='Bajo' else 'Otras casas' )
            ca.copy('utm_z', 'Altitud', ca.toZ)
            ca.copy('descripcion', 'Descripc')
            ca.copy('utm_x', 'x')
            ca.copy('utm_y', 'y')
            
            newFeatures.append(ca.getNewFeature())
    
        self._saveLayer(newFeatures)


puntos_vivienda = PuntosVivienda('honduras_puntorepr', 'puntos_viviendas')
puntos_vivienda.execute()