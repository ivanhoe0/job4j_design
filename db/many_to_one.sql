create table flight(
	id serial primary key,
	town_from varchar(255),
	town_to varchar(255)
);
create table ticket(
	id serial primary key,
	seat varchar(255),
	flight_id int references flight(id)
);