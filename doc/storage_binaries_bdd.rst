OID (BLOBS)
===========

Para cada fichero binario que se sube a la bdd se genera un código (OID) que lo identifica. En las tablas sólo se almacena el código en una columna de tipo OID o BLOB en el caso de ficheros muy grandes. El fichero binario se guarda en una estructura auxiliar de postgres.

Hay funciones de postgres para hacer inserts y selects: lo_import y lo_export, que permiten subir el fichero binario a la bdd a partir de una ruta y obtener el fichero binario desde la base de datos en una ruta determinada.

En los backups por defecto se ignoran este tipo de datos. Hay que especificar que se haga con opciones en el pg_dump.

Ejemplo:

CREATE TABLE test_images(name character varying, image oid); 

INSERT INTO test_images VALUES ('test',lo_import('/tmp/my_image.jpg');

SELECT lo_export('/tmp/image_from_db.jpg') FROM test_images WHERE name = 'test';


BYTEA
=====

Es un tipo de dato para almacenar ficheros binarios directamente en la bdd. El fichero se convierten en un string binario, por defecto hexadecimal, que representa el fichero y se almacena directamente en la tabla de la bdd que queramos, como un campo más.

Para hacer un insert hay que convertir primero el fichero binario a un Byte. Los select se pueden hacer directamente pero devuelve el string binario que representa al fichero. Por tanto la codificación y decodificación del fichero binario a/desde el string binario hay que hacerlo en el lado de la aplicación cliente.

El backup se hace por defecto ya que se trata como un campo más.

Función postgres para codificar imagen en Bytea:

create or replace function bytea_import(p_path text, p_result out bytea) 
                   language plpgsql as $$
declare
  l_oid oid;
  r record;
begin
  p_result := '';
  select lo_import(p_path) into l_oid;
  for r in ( select data 
             from pg_largeobject 
             where loid = l_oid 
             order by pageno ) loop
    p_result = p_result || r.data;
  end loop;
  perform lo_unlink(l_oid);
end;$$;

insert into my_table(bytea_data) select bytea_import('/my/file.name');
