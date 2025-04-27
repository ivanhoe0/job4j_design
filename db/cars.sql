create table car_bodies(
	id serial primary key,
	name varchar(255)
);
create table car_engines(
	id serial primary key,
	name varchar(255)
);
create table car_transmissions(
	id serial primary key,
	name varchar(255)
);
create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Хэтчбек');
insert into car_bodies(name) values('Пикап');
insert into car_bodies(name) values('Универсал');

insert into car_engines(name) values('Бензиновый двигаетль');
insert into car_engines(name) values('Дизельный двигатель');

insert into car_transmissions(name) values('Механическая коробка');
insert into car_transmissions(name) values('Автоматическа коробка');
insert into car_transmissions(name) values('Вариатор');

insert into cars(name, body_id, engine_id, transmission_id) values('Toyota', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('BMW', 1, null, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Shkoda', 2, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Ford', 3, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Mercedes', 1, 1, null);

select 
	c.id, 
	c.name car_name,
	b.name body_name,
	e.name engine_name,
	t.name transmission_name
from cars c left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id =t.id;

select
	b.name body_name
from car_bodies b left join cars c on b.id = c.body_id
where c.id is null;

select
	e.name engine_name
from car_engines e left join cars c on e.id = c.engine_id
where c.id is null;

select
	t.name transmissions_name
from car_transmissions t left join cars c on t.id = c.transmission_id
where c.id is null;