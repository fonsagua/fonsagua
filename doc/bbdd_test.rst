Introducción
============

Para incrementar la calidad del proyecto, en fonsagua usamos la herramienta pgtap para realizar tests sobre la base de datos. Lo que siguen son las instrucciones básicas de uso, y los casos que estamos probando

Instalación
===========

* Descarga pgtap desde http://pgxn.org/dist/pgtap/
* Si tu configuración de postgresql es estándar y usas ubuntu, para instalar pgtap usamos

::
  sudo apt-get install postgresql-server-dev-9.1
  make
  sudo make install
  make installcheck PGUSER=postgres

* Para instalarlo/desinstalarlo en una base de datos, podemos ejecutar a mano los sql correspondientes

::

  psql -d dbname -f `pg_config --sharedir`/contrib/pgtap.sql
  psql -d dbname -f `pg_config --sharedir`/contrib/uninstall_pgtap.sql

más sencillo y recomendable usar el sistema de extensiones de postgresql:
::
  psql -U postgres -d dbname -c "CREATE EXTENSION pgtap;"

* Lo más recomendable es instalarlo en el template_postgis de modo que las nuevas bases de datos creadas ya tengan la extensión disponible:

::
  psql -d template_postgis -c "CREATE EXTENSION pgtap;"




Como escribir un test
=====================

Crea un nuevo fichero con extension .sql en la carpeta *db/tests*. El fichero debe tener un contenido parecido al siguiente:
::
  BEGIN;
  SELECT plan(N);
  -- LOS TESTS
  SELECT * FROM finish();
  ROLLBACK;

Donde N es el número de tests que se ejecutarán desde ese fichero

Si quieres usar constantes a la hora de ejecutar los tests stackoverflow_ no viene mal. También puedes mirar *tests/indexes-geom-limites-administrativos.sql*

.. _stackoverflow: http://stackoverflow.com/questions/36959/how-do-you-use-script-variables-in-postgresql

Que testeamos en fonsagua, o cuando debería escribir un test
============================================================

* Que los triggers empleados cumplen el happy path
* Las tablas con geometrías usan índices espaciales
* Que todos los esquemas estén presentes y no haya ninguno a mayores
* Que todas las tablas están presentes y no haya ninguna a mayores


Cuando se ejecutan los tests
============================
El script *db/fonsagua.sh* nos permite crear una base de datos de desarrollo. Si pg_tap está instalado en el sistema el paso final del script es ejecutar todos los tests del directorio *db/tests*. El script asume que la extensión pgtap está creada en template_postgis.

Podemos pasar los tests a mano con:

pg_prove -h localhost -U fonsagua -d fonsagua tests/*.sql
