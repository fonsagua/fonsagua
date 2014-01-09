# -*- coding: utf-8 -*-

class PersTecnico(LayerMigration):
    def execute(self):
        newFeatures = []
    
        for ifeat in self.ilayer.getFeatures():
            ca = CopyAttributes(ifeat, self.ofields, self.ilayer)
            ca.copy('cod_abastecimiento', 'CodigoAb')
            ca.copy('nombre', 'Nombre')
            ca.copy('genero', 'Sexo', lambda v: 'Masculino' if v and v.upper().startswith('M') else 'Femenino')
            ca.copy('origen', 'Origen')
            
            newFeatures.append(ca.getNewFeature())
            
        self._saveLayer(newFeatures)
        
perstecnico = PersTecnico('honduras_abastecimiento_perstecnico', 'personal_tecnico')
perstecnico.execute()