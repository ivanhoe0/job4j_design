create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.14
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax
	after insert
	on products
	referencing new table as inserted
	for each statement
	execute procedure tax();
	
insert into products(name, producer, price) values('product_1', 'producer_1', 18);

create 
or replace function tax_before()
	returns trigger as 
$$
	BEGIN
		update products
		set price = price + price * 0.14
		where id = NEW.id;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_before
	before insert
	on products
	for each row
	execute procedure tax_before();

insert into products(name, producer, price) values('product_2', 'producer_1', 135);

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create 
or replace function add_to_hist()
	returns trigger as 
$$
	BEGIN
		insert into history_of_price(name, price, date) 
		values(NEW.name, NEW.price, current_date);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger ins_to_hist
	after insert
	on products
	for each row
	execute procedure add_to_hist();

insert into products(name, producer, price) values('product_3', 'producer_1', 140);

select * from history_of_price;
select * from products;