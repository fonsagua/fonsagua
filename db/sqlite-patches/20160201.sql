BEGIN;

update _map set visible = 'false' where nombre_capa = 'honduras';

UPDATE version SET version = '20160204';

END;
