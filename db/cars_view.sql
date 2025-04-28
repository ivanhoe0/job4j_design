create view cars_and_parts as
select c.id id, c.name car_name, b.name body_name, e.name engine_name, t.name transmission_name from cars c left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select * from cars_and_parts;