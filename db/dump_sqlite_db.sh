#!/bin/sh

# http://stackoverflow.com/questions/75675/how-do-i-dump-the-data-of-some-sqlite3-tables/37296788
# http://stackoverflow.com/questions/4199850/sqlite-export-with-column-names

TABLES='comunidades puntos_viviendas entrevistadores entrevistados subcuencas adescos cargos_publicos ongs otras_organizaciones tipos_cultivos produccion_consumo areas_potenciales_riego ganaderia cooperativas centros_educativos centros_salud otros_servicios capacitaciones_riesgos amenazas implicacion_comunidad abastecimientos r_abastecimientos_comunidades valoracion_sistema datos_consumo habitos_consumo fuentes_contaminacion juntas_agua personal_tecnico bombeos cobertura captaciones dep_intermedios dep_distribucion tuberias gest_comercial gest_financiera evaluacion fuentes r_abastecimientos_fuentes aforos niveles_freaticos analiticas comunidades_croquis alternativas preferencias_disenho comunidades_implicadas fuentes_implicadas alt_embalses alt_fuentes alt_depositos alt_tuberias alt_bombeos alt_conexiones alt_valvulas presupuesto  carreteras curvas_nivel_10m rios equipamientos puertos_aeropuertos caserios_comunidades areas_protegidas_2011 usos_suelo_2003 oceano honduras paises_limitrofes departamentos municipios cantones'

echo 'BEGIN TRANSACTION;' > /tmp/backup.sql
echo '' >> /tmp/backup.sql
for t in $TABLES ; do
    COLS=`sqlite3 $1 "pragma table_info(${t})" | cut -d'|' -f2`
    SELECT_COLS=`echo $COLS | sed -e 's/ /,/g'`
    INSERT_COLS=`echo $SELECT_COLS | sed -e 's/GEOMETRY/geom/'`
    COLS_CS=`echo $COLS | sed 's/ /,/g'`
    # echo -e ".dump ${t}" | sqlite3 $1 | grep -Pzo "(?s)^INSERT.*?\);$" | grep -v -e 'PRAGMA foreign_keys=OFF;' -e 'BEGIN TRANSACTION;' -e 'COMMIT;' |sed "s/^INSERT INTO \"${t}\"/INSERT INTO \"${t}\" (${COLS_CS})/" >> /tmp/backup.sql
    echo -e ".mode insert ${t}\nselect ${SELECT_COLS} from ${t};\n" | sqlite3 $1 | sed "s/^INSERT INTO ${t}/INSERT INTO ${t} (${INSERT_COLS})/" >> /tmp/backup.sql
done

echo '' >> /tmp/backup.sql
echo 'COMMIT;' >> /tmp/backup.sql
