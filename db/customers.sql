CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers(first_name, last_name, age, country) values('Mike', 'Davis', 30, 'USA');
insert into customers(first_name, last_name, age, country) values('Иван', 'Иванов', 27, 'Россия');
insert into customers(first_name, last_name, age, country) values('John', 'Black', 47, 'USA');
insert into customers(first_name, last_name, age, country) values('Мария', 'Иванова', 27, 'Россия');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders(amount, customer_id) values(45, 1);
insert into orders(amount, customer_id) values(4, 3);
insert into orders(amount, customer_id) values(123, 4);
insert into orders(amount, customer_id) values(11, 1);

select * from customers where id not in(select customer_id from orders);