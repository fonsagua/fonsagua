# -*- coding: utf-8 -*-

class LayerMigration():
    def __init__(self, ilayername, olayername):
        self.ilayer = ilayer = [x for x in iface.legendInterface().layers() if x.name() == ilayername][0]
        self.olayer = [x for x in iface.legendInterface().layers() if x.name() == olayername][0]
        self.olayer.dataProvider().clearErrors()
        self.ofields = self.olayer.dataProvider().fields()
        caps = self.olayer.dataProvider().capabilities()
        if not (caps & QgsVectorDataProvider.AddFeatures):
            raise Exception("La capa %s no es editable, o algo parecido" % (olayername))
        
    def _saveLayer(self, features):
        (res, foo) = self.olayer.dataProvider().addFeatures(features)
        if not res:
            raise Exception("Error guardando: %s\n\n%s" %(self.olayer.name(), self.olayer.dataProvider().errors()))
        print "%s parece correcta" % (self.olayer.name())
