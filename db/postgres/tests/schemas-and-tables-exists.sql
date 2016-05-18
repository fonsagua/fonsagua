BEGIN;
SELECT PLAN(4);

SELECT schemas_are(ARRAY[ 'public', 'elle', 'fonsagua', 'dominios', 'limites_administrativos' ]);

SELECT tables_are(
    'public',
    ARRAY[ 'geometry_columns', 'spatial_ref_sys' ]
);

SELECT tables_are(
    'elle',
    ARRAY[ '_map', '_map_style', '_map_overview', '_map_overview_style' ]
);

SELECT tables_are(
    'limites_administrativos',
    ARRAY['departamentos', 'municipios', 'cantones' ]
);

SELECT * from finish();
ROLLBACK;
