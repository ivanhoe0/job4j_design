create table books(
	id serial primary key,
	name varchar(255),
	author text,
	number_of_pages
);
insert into books(name, author, number_of_pages) values('Войнв и мир', 'Л.Н.Толстой', 960);
update books set name = 'Война и мир' where id =1;
delete from books where id = 1;