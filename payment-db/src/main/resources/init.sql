drop schema if exists payment cascade;
create schema payment;

drop user if exists pay;
create user pay password 'pay' valid until 'infinity';

grant all on schema payment to pay;

grant all on all sequences in schema payment to pay;
grant all on all functions in schema payment to pay;

create table payment.pm_clients (
	client_id varchar(64) primary key,
	name varchar(40),
	description text
);

create table payment.pm_accounts (
	account_id varchar(64) primary key,
	client_id varchar(64),
	account_number varchar(24),
	ogrn varchar(26),
	kpp varchar(18),
	inn varchar(24),
	account_status_id varchar(64),
	foreign key (account_status_id) references payment.pm_account_statuses(id),
	foreign key (client_id) references payment.pm_clients(client_id)
);

create table payment.pm_account_statuses (
	id varchar(64) primary key,
	status varchar(14)
);

create table payment.pm_payments (
	payment_private_id varchar(64) primary key,
	payment_public_id varchar(64),
	client_id varchar(64),
	receiver_account_id varchar(64),
	sender_account_id varchar(64),
	amount numeric,
	key varchar(64),
	foreign key (client_id) references payment.pm_clients(client_id),
	foreign key (receiver_account_id) references payment.pm_accounts(account_id),
	foreign key (sender_account_id) references payment.pm_accounts(account_id)
);

create table payment.pm_accepts (
	accept_id varchar(64) primary key,
	payment_private_id varchar(64),
	client_callback_url varchar(400),
	client_payment_url varchar(400),
	key varchar(64),
	accept_status_id varchar(64),
	foreign key (payment_private_id) references payment.pm_payments(payment_private_id),
	foreign key (accept_status_id) references payment.pm_accept_statuses(id)
);

create table payment.pm_accept_statuses (
	id varchar(64) primary key,
	status varchar(36)
);