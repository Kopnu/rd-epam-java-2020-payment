insert into payment.pm_account_statuses (id, status) values
('1','Активен'),
('2','Закрыт');

insert into payment.pm_accounts (account_id, client_id, account_number, ogrn, kpp, inn, account_status_id) values
('1','1','898989898912','12345678901238','098765432','123456789012','1'),
('2','1','898989898923','12345678902349','087654321','123456789123','2');

insert into payment.pm_clients (client_id, name, description) values
('1','Гоша', 'Описание Гоши'),
('2','Бука','Описание Буки');

insert into payment.pm_payments (payment_private_id, payment_public_id, client_id, reciever_account_id, sender_account_id,amount,key) values
('1','1','1','2','1','10000','123445532'),
('2','2','2','1','2','999999','243532');

insert into payment.pm_accept_statuses (id, status) values
('1','Принят в обработку'),
('2','Выставлен'),
('3','Оплачен'),
('4','Ошибка');

insert into payment.pm_accepts (accept_id, payment_public_id, client_callback_url,client_payment_url,key,accept_status_id) values
('1','1','firtsclient','secondclient','42523906','1'),
('2','2','secondclient','firstclient','3575732','3');