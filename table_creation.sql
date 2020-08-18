--user table
create table users(
  u_id serial primary key,
  first_name varchar(50),
  last_name varchar(50),
  username varchar(50),
  pass varchar(50),
  u_type int);

--account table
create table accounts(
  a_id serial primary key,
  balance decimal(10,5),
  a_type varchar(50),
  status varchar(50),
  approved_by varchar(100),
  created_on varchar(25)
);

--personal info table
create table information(
	u_id integer references users(u_id) primary key,
	ssn varchar(9),
	address varchar(50),
	city varchar(50),
	state varchar(25),
	zip varchar(25),
	phone varchar(15),
	email varchar(50));

--account cross reference table
create table acctxref(
  tb_id serial primary key,
  user_id integer references users(u_id),
  account_id integer references accounts(a_id)
);

-- -- -- sample values
-- users
insert into users(first_name,last_name,username,u_type) values ('John','Doe','bigjon78',2);
insert into users(first_name,last_name,username,u_type) values ('Janet','Dyne','waspinator23',2);
insert into users(first_name,last_name,username,u_type) values ('Tony','Marx','smalltone90',2);
insert into users(first_name,last_name,username,u_type) values ('Steve','Jacobs','gosteve72',1);
insert into users(first_name,last_name,username,u_type) values ('Marissa','Ruiz','ruiz68',0);

-- set all passwords to the hashed value of 'root'
update users set pass='4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2';

-- accounts
insert into accounts(balance,a_type,status,approved_by,created_on) values (600.89,'Checking','Open','Marissa Ruiz','02-25-2006');
insert into accounts(balance,a_type,status,approved_by,created_on) values (3400.39,'Savings','Open','Marissa Ruiz','02-25-2006');

-- acctxref
insert into acctxref(user_id,account_id) values (2,1);
insert into acctxref(user_id,account_id) values (2,2);

-- information
insert into information(u_id,ssn,address,city,state,zip,phone,email) values (1,'956251456','90 South St.','Norwalk','CA','65941','7412588989','doester@gmail.com');
insert into information(u_id,ssn,address,city,state,zip,phone,email) values (2,'606558888','454 Blue Road','Ontario','TX','95899','9658451000','wasper@aol.com');

-- show users and their info
select * from users
	left join information on
		users.u_id = information.u_id;

-- show users and their accounts
select u_id,first_name,last_name,username,balance,a_type,status from acctxref
  left join users on users.u_id = acctxref.user_id
  left join accounts on accounts.a_id = acctxref.account_id;

-- show open accounts using function
create or replace function openAccounts() returns table
	(fullName varchar(100), acctInfo text) as
$func$
	select concat(first_name,' ', last_name,),
			concat('#',a_id,', $', balance,',  ', a_type)
	from acctxref
		left join accounts on acctxref.account_id = accounts.a_id
		left join users on acctxref.user_id = users.u_id
	where accounts.status = 'Open';
$func$ language sql;

select openAccounts();

-- opened in a specific year using function
create or replace function openedOnYear(yearCreated varchar(25)) returns table
	(accs int) as
$func$
	select a_id from accounts where created_on like yearCreated;
$func$ language sql;

select openedOnYear('%2020');
