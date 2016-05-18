# -*- coding: utf-8 -*-

SQLITE_HONDURAS_PATH='/home/fpuga/Escritorio/migracion/130918_data_Marcovia/_DB/fonsagua.sqlite3'

import sqlite3

class CopyAttributes():
    def __init__(self, ifeat, ofields, ilayer, pkname=None):
        self.ilayer = ilayer
        self.iatts = ifeat.attributes()
        self.of = QgsFeature()
        self.of.setFields(ofields)
        if ilayer.hasGeometryType():
            if ifeat.geometry().wkbType() != QGis.WKBPoint and ifeat.geometry().wkbType() != QGis.WKBMultiPoint:
                res = ifeat.geometry().convertToMultiType()
                if not res:
                    raise Exception("Error al convertir a Multitype")
            self.of.setGeometry(ifeat.geometry())
        
        self.codigoscomunidad = [x for x in iface.legendInterface().layers() if x.name() == 'comunidades'][0].dataProvider().uniqueValues(1)
        self.codigosabastecimiento = [x for x in iface.legendInterface().layers() if x.name() == 'abastecimientos'][0].dataProvider().uniqueValues(0)
        
        self.pkname = pkname
        if pkname:
            self.pkvalue =  ifeat.attributes()[ilayer.fieldNameIndex(pkname)]

    def copy(self, o, i, processfunction=None):
        '''
        copy of inputFeature.i attr to ouputFeature.o attr
        if processfunction is provided applies it first to to the value of i
        '''
        ivalue = self.iatts[self.ilayer.fieldNameIndex(i)]
        
        if isinstance(ivalue, basestring):
            if len(ivalue) >= 240:    
                ivalue = self.fromsqlite(o, i) or ivalue
            
        if processfunction:
            self.of.setAttribute(o, processfunction(ivalue))
        else:
            self.of.setAttribute(o, ivalue)

    def fromsqlite(self, o, i):
        ivalue = None
        query = "SELECT %s FROM %s WHERE %s = '%s'" % (i, self.ilayer.name().replace('honduras_', ''), self.pkname, self.pkvalue)
        try:
            conn = sqlite3.connect(SQLITE_HONDURAS_PATH)
            c = conn.cursor()
            ivalue = c.execute(query).fetchall()[0][0]
        except IndexError:
            print query
            ivalue = None
        finally:
            c.close()
            conn.close()
            
        return ivalue
            
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
    
    def str2meters(self, v):
        try:
            return int(v)
        except ValueError:
            if (v.endswith('km')):
                return int(v[:-2]) * 1000
            else:
                if (v.endswith('metros')):
                    return int(v[:-6])
                else:
                    if (v.endswith('m')):
                        return int(v[:-1])
            return None
        except TypeError:
            return v
        
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
    
    def toCodigoAB(self, v):
        '''
        takes 8 characters from v and use it as codigoc if that value is valid codigoab
        if not DUMB is used as codigoab
        '''
        codigoab = v[0:8]
        if codigoab in self.codigosabastecimiento:
            return codigoab
        print "Codigo abastecimiento no existe: %s, usamos DUMB" % (codigoab) 
        return 'DUMB'
    
    def toDate(self, v):
        '''
        tries to build a date in the format dd/mm/aaaa
        Now only replace - by /
        '''
        return v.replace('-', '/') if v else v
    
    def toZ(self, v):
        '''
        tries to handle correctly fields that represents height or z coordinate
        no, it only returns None if z is less than 0 
        '''
        return v if v and int(v) >= 0 else None
    
    def gal2metroc(self, v):
        if v:
            return v/264.17
        return 0
        
    def getNewFeature(self):
        return self.of