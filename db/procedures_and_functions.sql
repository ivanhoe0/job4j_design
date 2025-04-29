select * from products;

create or replace procedure delete_product(p_count integer, p_price integer)
language 'plpgsql'
as $$
	BEGIN
		delete from products where price = p_price;
		delete from products where count > p_count;
	END;
$$;


insert into products(name, producer, count, price) values('product_5', 'producer_1', 42, 145);
insert into products(name, producer, count, price) values('product_6', 'producer_1', 12, 11);
insert into products(name, producer, count, price) values('product_5', 'producer_1', 42, 11);

call delete_product(10, 21);

create or replace function f_delete_product(p_count integer, p_price integer)
returns integer
language 'plpgsql'
as
$$
	declare
		result integer;
	begin
		select into result sum(count) from products where count > p_count;
		delete from products where price = p_price;
		delete from products where count > p_count;
		return result;
	end;
$$;

select f_delete_product(10, 11);