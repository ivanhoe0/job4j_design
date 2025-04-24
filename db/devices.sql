insert into devices(name, price) values('smartphone', 19034.90);
insert into devices(name, price) values('laptop', 150000);
insert into devices(name, price) values('headphones', 16499.90);

insert into people(name) values('Ivan');
insert into people(name) values('Maria');
insert into people(name) values('Alexander');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(3, 3);

select avg(price) from devices;

select p.name, avg(d.price) 
from people p 
join devices_people dp on p.id = dp.people_id 
join devices d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price) 
from people p 
join devices_people dp on p.id = dp.people_id 
join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 500;