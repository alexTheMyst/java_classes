-- get all cars with parts names
select
  car.name,
  transmission.name,
  engine.name,
  gear.name
from cars car join transmissions transmission on car.transmission_id = transmission.id
  join engines engine on car.engine_id = engine.id
  join gears gear on car.gear_id = gear.id;

-- get unused transmissions using outer join
select transmission.name
from transmissions transmission left join cars car on transmission.id = car.transmission_id
where car.id is null ;

-- get unused transmissions using in
select transmission.name
from transmissions transmission
where id not in (select transmission_id from cars);

-- get unused transmissions using exists
select transmission.name
from transmissions transmission
where not exists (select id
                  from cars c
                  where transmission.id = c.transmission_id);

-- get unused engines using outer join
select engine.name
from engines engine left join cars car on engine.id = car.engine_id
where car.id is null ;

-- get unused gears using outer join
select gear.name
from gears gear left join cars car on gear.id = car.gear_id
where car.id is null ;
