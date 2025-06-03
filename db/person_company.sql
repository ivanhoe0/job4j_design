CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values(1, 'sber');
insert into company(id, name) values(2, 'AOA');
insert into company(id, name) values(3, 'yandex');
insert into company(id, name) values(4, 'BAS');
insert into company(id, name) values(5, 'lukoil');

insert into person(id, name, company_id) values(1, 'Ivan', 1);
insert into person(id, name, company_id) values(2, 'Petr', 2);
insert into person(id, name, company_id) values(3, 'Maria', 3);
insert into person(id, name, company_id) values(4, 'Elen', 1);
insert into person(id, name, company_id) values(5, 'Nikolay', 5);
insert into person(id, name, company_id) values(6, 'Aleksey', 5);
insert into person(id, name, company_id) values(7, 'Dmitriy', 2);
insert into person(id, name, company_id) values(8, 'Egeniy', 1);
insert into person(id, name, company_id) values(9, 'Dmitriy', 2);

select p.name, c.name 
from person p join company c on p.company_id = c.id
where c.id !=5;


with person_company as (
select c.name, count(*) count
from person p join company c on p.company_id = c.id
group by c.name)
select * from person_company where count = (select max(count) from person_company);