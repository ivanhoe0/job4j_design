create table type(
	id serial primary key,
	name varchar(255)
);
create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expire_date date,
	price float
);
insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('ХЛЕБ');
insert into type(name) values('МОРОЖЕНОЕ');

insert into product(name, type_id, expire_date, price) values('Сыр плавленый', 1, '30.04.2025', 45.30);
insert into product(name, type_id, expire_date, price) values('Сыр моцарелла', 1, '02.05.2025', 75.15);
insert into product(name, type_id, expire_date, price) values('Молоко 2,5%', 2, '23.04.2025', 75.15);
insert into product(name, type_id, expire_date, price) values('Молоко 3,2%', 2, '27.04.2025', 81);
insert into product(name, type_id, expire_date, price) values('Хлеб белый', 3, '28.04.2025', 15.55);
insert into product(name, type_id, expire_date, price) values('Мороженое в стаканчике', 4, '25.06.2025', 45);
insert into product(name, type_id, expire_date, price) values('Мороженое в контейнере', 4, '18.07.2025', 81);

select p.* from product p join type t on p.type_id = t.id
where t.name = 'СЫР';

select * from product where name like '%Мороженое%';

select * from product where expire_date < current_date;

select * from product where price in(select max(price) from product);

select t.name "Имя_типа", count(*) "Количество" from product p join type t on p.type_id = t.id
group by t.name;

select p.* from product p join type t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name "Имя_типа", count(*) "Количество" from product p join type t on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.* , t.name "Тип" from product p join type t on p.type_id = t.id;