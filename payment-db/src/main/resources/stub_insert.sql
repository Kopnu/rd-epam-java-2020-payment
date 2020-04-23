insert into payment.pm_account_statuses (id, status) values
	('1','Активен'),
	('2','Закрыт');

insert into payment.pm_accept_statuses (id, status) values
	('1','Принят в обработку'),
	('2','Выставлен'),
	('3','Оплачен'),
	('4','Ошибка');

insert into payment.pm_clients (client_id, status, description) values
	('1', 'Dzimitry', 'самобытно'),
	('2', 'Вася', 'не самобытно')

insert into payment.pm_accounts (account_id, client_id, account_number, ogrn, kpp, inn, account_status_id) values
	('1','1', '89188114787', '12345677654321', '897564231', '123456654321', '1'),
	('2','1', '89748775487', '76543211234567', '123456789', '654321123456', '2');

insert into payment.pm_payments (payment_private_id, payment_public_id, client_id, reciever_account_id, sender_account_id,amount,key) values
	('1','1','1','2','1','100500','87778887777'),
	('2','2','2','1','2','1','54878548');

insert into payment.pm_accepts (accept_id, payment_private_id, client_callback_url,client_payment_url,key,accept_status_id) values
	('1','1','qwertyqwerty','ytrewqytrewq','33443321','1'),
	('2','2','ytrewqytrewq','qwertyqwerty','35232333','3');