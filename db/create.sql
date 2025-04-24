create table roles(
	id serial primary key,
	role_name varchar(255)
);
create table users(
	id serial primary key,
	login varchar(255),
	role_id int references role(id)
);
create table rules(
	id serial primary key,
	create_item boolean,
	update_item boolean
);
create table roles_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);
create table states(
	id serial primary key,
	state varchar(255)
);
create table categories(
	id serial primary key,
	category varchar(255)
);
create table items(
	id serial primary key,
	item varchar(255),
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);
create table comments(
	id serial primary key,
	comment varchar(255),
	item_id int references items(id)
);
create table attachs(
	id serial primary key,
	item_id ine references items(id)
);