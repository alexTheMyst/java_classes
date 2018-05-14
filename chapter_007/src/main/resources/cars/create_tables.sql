create table transmissions (
  id serial primary key ,
  name varchar(100)
);

create table engines (
  id serial primary key ,
  name varchar(100)
);

create table gears (
  id serial primary key ,
  name varchar(100)
);

create table cars (
  id serial primary key ,
  name varchar(100),
  transmission_id integer references transmissions(id),
  engine_id integer references engines(id),
  gear_id integer references gears(id)
);