insert into dominios.tipo values ('Casa mas baja');

alter table fonsagua.otros_servicios drop constraint otros_servicios_tipo_servicio_fkey, add constraint otros_servicios_tipo_servicio_fkey foreign key (tipo_servicio) references dominios.tipo_servicio(item) on update cascade;
update dominios.tipo_servicio set item='Instalación deportiva' where item='Intalación deportiva';