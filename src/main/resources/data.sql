insert into profile(name) values ('ROLE_COSTUMER');

insert into user(user_name, password) values ('Gilberto', '$2a$10$mfoM1/6BxdpuP0Qx69RGvO1LAEcvXiXfoM33Sqy2M3P1isU0U8Bf6');
insert into user(user_name, password) values ('Alexandre', '$2a$10$mfoM1/6BxdpuP0Qx69RGvO1LAEcvXiXfoM33Sqy2M3P1isU0U8Bf6');
insert into user(user_name, password) values ('Ariene', '$2a$10$mfoM1/6BxdpuP0Qx69RGvO1LAEcvXiXfoM33Sqy2M3P1isU0U8Bf6');

insert into bank(name, cnpj, endereço) values ('Nubank', '1212334', 'Rua 0');
insert into bank(name, cnpj, endereço) values ('Itau', '44324324', 'Rua 1');
insert into bank(name, cnpj, endereço) values ('Santander', '442134324', 'Rua 2');

insert into account(number, owner_id, balance, password, bank_id, type) values ('500', 1, 1910, '123', 1, 'CORRENTE');
insert into account(number, owner_id, balance, password, bank_id, type) values ('501', 2, 1000, '123', 1, 'POUPANÇA');
insert into account(number, owner_id, balance, password, bank_id, type) values ('502', 3, 1500, '123', 1, 'SALARIO');

insert into user_profiles(user_id, profiles_id) values (1, 1);
insert into user_profiles(user_id, profiles_id) values (2, 1);
insert into user_profiles(user_id, profiles_id) values (3, 1);