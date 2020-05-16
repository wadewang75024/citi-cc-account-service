-- Dont forget to add AUTO_INCREMENT to avoid "No Default value" exception in Java
-- Use timestamp to see both date and time
use mydb;
CREATE TABLE citi_accounts 
(account_id bigInt not null AUTO_INCREMENT, 
customer_id bigInt not null,
type VARCHAR(10),
amount DECIMAL(13,2),
primary key (account_id));

-- Misc
delete from citi_accounts

select * from citi_accounts

drop table citi_accounts;

insert into citi_accounts
values (0, 1, "Checking", 100.99);

