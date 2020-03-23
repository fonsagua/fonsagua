-- Algunos ajustes de la capa caserios_comunidades al INE de 2013
-- Ver documento `unir_y_separar_datos_municipales.md` para más información

DELETE FROM caserios_comunidades WHERE cod_caseri IN ('06060109', '06060621', '06060906', '06060936', '06070106', '06070109', '06070113', '06070114', '06070209', '06070806', '06070807', '06070808', '06070810', '06070902', '06070919', '06071204', '06071405', '06071409', '06071412', '06071426', '06071429', '06071432', '06071433', '06071434', '06071445', '06071450', '06071452', '06071454', '06071480', '06071481', '06072009');



/*
Para recoger los datos del original

for i in `find "${WORKING_BACKUP}" -iname 'fonsagua.sqlite'` ; do
    echo $i
    spatialite -bail -silent "${i}" "select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || cod_comunidad || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06060809'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06060900' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06060906'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06070203' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06073601'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06070216' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06070205'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06070222' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06070207'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06071447' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06073501'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06071604' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06071605'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06071614' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06071604'); select 'INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES (''' || '06071709' || ''', ''nombre'',  MakePoint(' || st_x(geom) || ', ' || st_y(geom) || ', 32616))' from comunidades where cod_comunidad IN ('06073301');"
done
*/


DELETE FROM caserios_comunidades WHERE cod_caseri IN ('06060809', '06060910', '06070203', '06070216', '06070222', '06071447', '06071604', '06071614', '06071709');
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06060809', 'nombre',  MakePoint(500355.211999301, 1452697.57563011, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06060910', 'nombre',  MakePoint(502235.477093573, 1442729.82109723, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06070203', 'nombre',  MakePoint(454169.440138309, 1452162.12468812, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06070216', 'nombre',  MakePoint(453341.97695023, 1457192.68585235, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06070222', 'nombre',  MakePoint(453326.927320319, 1456032.36834961, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06071447', 'nombre',  MakePoint(457432.599639603, 1457272.30154216, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06071604', 'nombre',  MakePoint(451565.38956253, 1461984.19804781, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06071614', 'nombre',  MakePoint(451602.059915299, 1461454.17608127, 32616));
INSERT INTO caserios_comunidades (cod_caseri, caserio, geom) VALUES ('06071709', 'nombre',  MakePoint(447616.800106547, 1466263.60347996, 32616));


UPDATE caserios_comunidades SET caserio = 'Río Grande No.2' WHERE cod_caseri = '06060801';
UPDATE caserios_comunidades SET caserio = 'Rio Grande # 3' WHERE cod_caseri = '06060809';
UPDATE caserios_comunidades SET caserio = 'Col. Nuevo Modelo o las Crucitas' WHERE cod_caseri = '06060910';
UPDATE caserios_comunidades SET caserio = 'El Barrial' WHERE cod_caseri = '06061004';
UPDATE caserios_comunidades SET caserio = 'El Llano' WHERE cod_caseri = '06061101';
UPDATE caserios_comunidades SET caserio = 'El Venado' WHERE cod_caseri = '06070203';
UPDATE caserios_comunidades SET caserio = 'Rafael Leonardo Callejas' WHERE cod_caseri = '06070216';
UPDATE caserios_comunidades SET caserio = 'La Coquera' WHERE cod_caseri = '06070222';
UPDATE caserios_comunidades SET caserio = 'La Sepa' WHERE cod_caseri = '06070914';
UPDATE caserios_comunidades SET caserio = 'El Ojachalito o La Nueva' WHERE cod_caseri = '06071447';
UPDATE caserios_comunidades SET caserio = 'Colonia 3 de Febrero' WHERE cod_caseri = '06071604';
UPDATE caserios_comunidades SET caserio = 'La nueva boca del rio viejo' WHERE cod_caseri = '06071614';
UPDATE caserios_comunidades SET caserio = 'El Carretal' WHERE cod_caseri = '06071709';


