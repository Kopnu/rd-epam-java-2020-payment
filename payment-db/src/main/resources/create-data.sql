insert into payment.pm_account_statuses (id, status) values
	('10', 'Активен'),
	('20', 'Закрыт');

insert into payment.pm_accounts (account_id, client_id, account_number, ogrn, kpp, inn, account_status_id) values
	('1', '3', '349732465125', '1053600591197', '773301001', '366406939742', '20'),
	('2', '2', '801547395091', '3972491975663', '319028573', '397159439271', '10'),
	('3', '1', '931849799721', '1724919728523', '982636273', '221767597362', '10');

insert into payment.pm_clients (client_id, name, description) values
	('1', 'Костя', 'Описание 1'),
	('2', 'Алексей', ''),
	('3', 'Даниил', 'Описание 3');

insert into payment.pm_payments (payment_private_id, payment_public_id, client_id, receiver_account_id, sender_account_id,amount,key) values
	('1', '1', '1', '3', '3', '500000', '1BC29B36F623BA82AAF6724FD3B16718'),
	('2', '2', '2', '1', '1', '20000', '1BC29BD43L242A82AAF6724FD3B16718');

insert into payment.pm_accept_statuses (id, status) values
	('10', 'Принят в обработку'),
	('20', 'Выставлен'),
	('30', 'Оплачен'),
	('40', 'Ошибка');

insert into payment.pm_accepts (accept_id, payment_private_id, client_callback_url,client_payment_url,key,accept_status_id) values
	('1', '1', 'https://bank.ru/client/callback/1', 'https://bank.ru/client/payment/1', '1BC29B36F623BA82AAF6724FD3B16718', '40'),
	('2', '2', 'https://bank.ru/client/callback/2', 'https://bank.ru/client/payment/2', '1BC29BD43L242A82AAF6724FD3B16718', '10');