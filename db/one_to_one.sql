create table passport(
	id serial primary key,
	series int,
	number int
);
create table passenger(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	passport_id int references passport(id) unique
);