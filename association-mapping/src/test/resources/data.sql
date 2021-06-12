--customer record
insert into customer(id,name) values(1,'cust01');
insert into customer(id,name) values(2,'cust02');
insert into customer(id,name) values(3,'tom');
insert into customer(id,name) values(4,'garry');
insert into customer(id,name) values(5,'soniya');
--phone record
insert into phone_number(id,number) values(1,'348872932033');
insert into phone_number(id,number) values(2,'698506056654');
insert into phone_number(id,number) values(3,'695454545445');
insert into phone_number(id,number) values(4,'560590490455');
insert into phone_number(id,number) values(5,'506095445456');
insert into phone_number(id,number) values(6,'697609756605');
insert into phone_number(id,number) values(7,'799607676766');
insert into phone_number(id,number) values(8,'506565656565');
insert into phone_number(id,number) values(9,'568560556566');
insert into phone_number(id,number) values(10,'67906767676');
insert into phone_number(id,number) values(11,'59685065656');
insert into phone_number(id,number) values(12,'49584054054');

--customer-contacts
insert into customer_contacts(customer_id,phone_id) values(1,'1');
insert into customer_contacts(customer_id,phone_id) values(1,'2');
insert into customer_contacts(customer_id,phone_id) values(1,'3');
insert into customer_contacts(customer_id,phone_id) values(1,'4');
insert into customer_contacts(customer_id,phone_id) values(1,'5');

insert into customer_contacts(customer_id,phone_id) values(2,'6');
insert into customer_contacts(customer_id,phone_id) values(2,'7');

insert into customer_contacts(customer_id,phone_id) values(3,'8');

insert into customer_contacts(customer_id,phone_id) values(4,'9');
insert into customer_contacts(customer_id,phone_id) values(4,'10');
insert into customer_contacts(customer_id,phone_id) values(4,'11');

insert into customer_contacts(customer_id,phone_id) values(5,'12');