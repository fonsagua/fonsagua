# -*- coding: utf-8 -*-

class NFreatPozo(LayerMigration):
    def execute(self):
        newFeatures = []
    
        for ifeat in self.ilayer.getFeatures():
            ca = CopyAttributes(ifeat, self.ofields, self.ilayer)
            ca.copy('cod_fuente', 'IdFuente')
            ca.copy('nivel', 'NFreatico')
            ca.copy('fecha', 'Fecha', ca.toDate)
            ca.copy('hora', 'Hora')
            
            newFeatures.append(ca.getNewFeature())
            
        self._saveLayer(newFeatures)
        
nfreatpozo = NFreatPozo('honduras_abastecimiento_nfreatpozo', 'niveles_freaticos')
nfreatpozo.execute()