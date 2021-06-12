--customer record
insert into customer(id,name) values(1,'cust01');
insert into customer(id,name) values(2,'cust02');
insert into customer(id,name) values(3,'tom');
insert into customer(id,name) values(4,'garry');
insert into customer(id,name) values(5,'soniya');
--customer-contacts
insert into customer_contacts(customer_id,phone_numbers) values(1,'2371837');
insert into customer_contacts(customer_id,phone_numbers) values(1,'245547');
insert into customer_contacts(customer_id,phone_numbers) values(1,'2233837');
insert into customer_contacts(customer_id,phone_numbers) values(1,'245323837');
insert into customer_contacts(customer_id,phone_numbers) values(1,'2454837');

insert into customer_contacts(customer_id,phone_numbers) values(2,'56891837');
insert into customer_contacts(customer_id,phone_numbers) values(2,'24871837');

insert into customer_contacts(customer_id,phone_numbers) values(3,'9071837');

insert into customer_contacts(customer_id,phone_numbers) values(4,'58971837');
insert into customer_contacts(customer_id,phone_numbers) values(4,'34371837');
insert into customer_contacts(customer_id,phone_numbers) values(4,'4793437');

insert into customer_contacts(customer_id,phone_numbers) values(5,'2328671837');

----------------------------------------------------------------------------------------
--order_detail
insert into order_detail(id,desc) values(1,'grocery');
insert into order_detail(id,desc) values(2,'electronics');
insert into order_detail(id,desc) values(3,'clothes');
insert into order_detail(id,desc) values(4,'untensil');
insert into order_detail(id,desc) values(5,'furniture');
--product_list
insert into product_list(order_id,name,amount) values(2,'apple phone',73794);
insert into product_list(order_id,name,amount) values(1,'sugar',90);
insert into product_list(order_id,name,amount) values(1,'rice',190);
insert into product_list(order_id,name,amount) values(2,'google',738349);
insert into product_list(order_id,name,amount) values(4,'spoon',10);
insert into product_list(order_id,name,amount) values(3,'t shirt',250);
insert into product_list(order_id,name,amount) values(3,'jeans',570);
insert into product_list(order_id,name,amount) values(5,'chair',340);
insert into product_list(order_id,name,amount) values(5,'table',1499.99);
insert into product_list(order_id,name,amount) values(5,'almirah',10238);
