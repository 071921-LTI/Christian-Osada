create table if not exists users(
	user_id SERIAL primary key,
	username VARCHAR(50) unique,
	pass VARCHAR(50) not null,
	perm_level INTEGER not null
);

create table if not exists items(
	item_id SERIAL primary key,
	item_name VARCHAR(50) not null,
	item_price VARCHAR(50) not null,
	owned_by INTEGER references users(user_id)
);

create table if not exists offers(
	offer_id SERIAL primary key,
	offerer_id INTEGER references users(user_id),
	item_id INTEGER references items(item_id),
	status VARCHAR(8) not null
);

insert into users (username, pass, perm_level) values ('testUser', 'testPassword ', 1);
insert into items (item_name, item_price, owned_by) values ('testItem', '$50 ', 1);
insert into offers (offerer_id, item_id, status) values (2, 1, 'default');