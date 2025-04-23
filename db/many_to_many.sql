create table passenger(
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);
create table flight(
	id serial primary key,
	town_from varchar(255),
	town_to varchar(255)
);
create table passengers_flights(
	id serial primary key,
	passenger_id int references passenger(id),
	flight_id int references flight(id)
);