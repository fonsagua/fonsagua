Este documento describe como desplegar un *dump* de una base de datos que funcione con la aplicación gvSIG Fonsagua para su uso en entornos de producción.

El dump de la base de datos contendrá:

* La lógica de la base de datos que necesita la aplicación para funcionar (como valores de comboboxes, campos automáticos, ...)
* Las creación de las tablas y los datos de las capas que se emplearán como cartografía base
* La creación de las tablas para las capas de proyecto
* La simbología creada para para las capas empleadas en la aplicación

Descripción de los esquemas
===========================
La base de datos está compuesta por los siguientes esquemas:

* **elle**: Esquema empleado para la configuración de vistas y simbologías predeterminadas empleadas por el plugin ELLE
* **dominios**: Esquema donde se guardan los datos empleados para rellenar los valores de los comboboxes en la aplicación
* **fonsagua**: Esquema empleado para las capas de proyecto de la aplicación. Es en este esquema donde los usuarios introducirán datos cuando interactuen con la aplicación.
* **c_base**: Esquema empleado para las capas de cartografía base


Requisitos previos
==================

Se requiere un servidor de base de datos PostgreSQL versión 9.0 o superior (la versión recomendada es la 9.1), con la extensión PostGIS versión 1.5.

Se requiere la creación de un usuario **fonsagua**, como propietario de la base de datos, tablas y demás elementos de la base de datos. Para ello se podría emplear el comando

createuser -h [SERVIDOR] -p [PUERTO] -U [SUPERUSUARIO] -SPDRl fonsagua

Se requiere la creación de una base de datos con soporte espacial. Suponiendo, la existencia de un *template* para la creación de bases de datos espaciales llamado *template_postgis*, se podría emplear para ello el comando:

createdb -h [SERVIDOR] -p [PUERTO] -U [SUPERUSUARIO] -T template_postgis --owner fonsagua BASE_DE_DATOS


Se debe asignar la propiedad del esquema *public*, y de las tablas de ese esquema al usuario **fonsagua**. Esto se puede hacer logueandose como superusuario en el servidor de base de datos y ejecutando las sentencias SQL

ALTER SCHEMA public OWNER TO fonsagua;
ALTER TABLE public.geometry_columns OWNER TO fonsagua;
ALTER TABLE public.spatial_ref_sys OWNER TO fonsagua;
 ALTER TABLE public.geography_columns OWNER TO fonsagua;


Restauración del dump
=====================

El dump se proporciona en un único fichero de texto plano a ejecutar como usuario **fonsagua**.

psql -h [SERVIDOR] -p [PUERTO] -U fonsagua -d [BASE DE DATOS] -f [FICHERO DE DUMP]
