create table departments(
	id serial primary key,
	name varchar(255)
);
create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);
insert into departments(name) values('IT');
insert into departments(name) values('accounting');
insert into departments(name) values('HR');
insert into departments(name) values('economic');

insert into employees(name, department_id) values('Alexander', 1);
insert into employees(name, department_id) values('Ivan', 1);
insert into employees(name, department_id) values('Maria', 2);
insert into employees(name, department_id) values('Petr', 2);
insert into employees(name, department_id) values('Sergey', 4);

select * from departments d left join employees e on d.id = e.department_id;
select * from departments d right join employees e on d.id=e.department_id;
select * from departments d full join employees e on d.id=e.department_id;
select * from departments cross join employees;

select d.* from departments d left join employees e on d.id = e.department_id where e.name is null;

select d.name, e.name from departments d left join employees e on d.id = e.department_id;
select d.name, e.name from employees e right join departments d on d.id = e.department_id;

create table teens(
	id serial primary key,
	name varchar(255),
	gender boolean
);

insert into teens(name, gender) values('Maria', '1');
insert into teens(name, gender) values('Ivan', '0');
insert into teens(name, gender) values('Vasiliy', '0');
insert into teens(name, gender) values('Kseniya', '1');
insert into teens(name, gender) values('Anastasia', '1');
insert into teens(name, gender) values('Petr', '0');
insert into teens(name, gender) values('Julia', '1');

select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender and t1.id > t2.id;