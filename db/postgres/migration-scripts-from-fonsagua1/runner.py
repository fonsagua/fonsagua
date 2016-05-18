# -*- coding: utf-8 -*-

# Para escribir menos
#ilayer = processing.getobject("comunidades")
#ifields = ilayer.dataProvider().fields().toList()
#for f in ifields:
#print "of.setAttribute('', iatts[ilayer.fieldNameIndex('%s')])"%(f.name())


##
## RECUERDA PONER EL ENCODING DE LA CAPA ORIGINAL EN WINDOWS-1250 EN LA CAPA ORIGINAL
##

#valoracion_sistema


execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/CopyAttributes.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/LayerMigration.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/abastecimientos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/relacion_comunidades_abastecimientos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/puntos_vivienda.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/amenazas.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/ccomunit.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/c_educativos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/captaciones.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/tuberias.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/dep_distribucion.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/bombas.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/c_salud.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/fuentes_contaminacion.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/manantiales.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/ptos_rios.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/pozos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/aforos_pozos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/abastecimiento_datosconsumo.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/noabastecimiento_habitosmedios.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/capacitaciones_riesgos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_cargos.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_subcuencas.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_entrevistadores.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_interlocutores.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_experienciasprevias.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_riegoorganizacion.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_consumocomparado.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/comunidades_riegoproduccioncomunidad.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/abastecimiento_nfreatpozo.py')
execfile('/home/development/fonsagua/fonsagua/db/migration-scripts-from-fonsagua1/abastecimiento_perstecnico.py')

# re-launch triggers
import os
os.system(' spatialite /home/fpuga/Escritorio/migracion/fonsagua.sqlite "update abastecimientos set gid = gid" ')
os.system(' spatialite /home/fpuga/Escritorio/migracion/fonsagua.sqlite "update comunidades set gid = gid" ')
