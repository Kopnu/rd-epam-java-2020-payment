drop schema if exists payment cascade;
create schema payment;

drop user if exists pay;
create user pay password 'pay' valid until 'infinity';

grant all on schema payment to pay;
grant all privileges on all sequences in schema payment to pay;
grant all privileges on all functions in schema payment to pay;