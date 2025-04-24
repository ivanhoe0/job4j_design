create table flights(
	id serial primary key,
	town_from varchar(255),
	town_to varchar(255)
);
create table tickets(
	id serial primary key,
	seat varchar(255),
	flight_id int references flights(id)
);
select t.seat "Место", f.town_from  "Откуда", f.town_to as "Куда" 
from tickets t join flights f on t.flight_id = f.id;

select t.seat, f.town_from 
from tickets t join flights f on t.flight_id = f.id;

select t.seat Место, f.town_from Откуда
from tickets t join flights f on t.flight_id = f.id where f.town_from = 'Moscow';