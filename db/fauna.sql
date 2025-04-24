create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);
insert into fauna values('fish', 100000, '10.07.1967');
insert into fauna values('bull', 20000, '11.12.2001');
insert into fauna values('wolverine', 11000, '04.03.1914');
select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';