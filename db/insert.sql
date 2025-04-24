insert into roles(role_name) values('administrator');
insert into users values('ivanova', 1);
insert into rules(create_item, update_item) values('true','true');
insert into roles_rules(role_id, rules_id) values(1, 1);
insert into states(state) values('active');
insert into categories values('urgent');
insert into items values('create new user', 1, 1, 1);
insert into comments values('urgent ticket', 1);
insert into attachs values(1);