insert into engines (name) values ('gas engine 2L');
insert into engines (name) values ('gas engine 3L');
insert into engines (name) values ('diesel engine 2L');
insert into engines (name) values ('diesel engine 2,5L');

insert into transmissions (name) values ('4x4');
insert into transmissions (name) values ('2x4 front');
insert into transmissions (name) values ('2x4 rear');

insert into gears (name) values ('automatic');
insert into gears (name) values ('robot');
insert into gears (name) values ('manual');


do $$
declare
  trans_id integer;
  engine_id integer;
  gear_id integer;
begin
  select id into trans_id from transmissions where name = '4x4';
  select id into engine_id from engines where name = 'gas engine 3L';
  select id into gear_id from gears where name = 'automatic';
  insert into cars (name, transmission_id, engine_id, gear_id) values ('cool jeep', trans_id, engine_id, gear_id);
end $$;

do $$
declare
  trans_id integer;
  engine_id integer;
  gear_id integer;
begin
  select id into trans_id from transmissions where name = '4x4';
  select id into engine_id from engines where name = 'gas engine 2L';
  select id into gear_id from gears where name = 'automatic';
  insert into cars (name, transmission_id, engine_id, gear_id) values ('SUV', trans_id, engine_id, gear_id);
end $$;

do $$
declare
  trans_id integer;
  engine_id integer;
  gear_id integer;
begin
  select id into trans_id from transmissions where name = '2x4 rear';
  select id into engine_id from engines where name = 'gas engine 3L';
  select id into gear_id from gears where name = 'robot';
  insert into cars (name, transmission_id, engine_id, gear_id) values ('fast sedan', trans_id, engine_id, gear_id);
end $$;
