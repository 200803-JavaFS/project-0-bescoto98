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
  balance money,
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
insert into users(first_name,last_name,username,pass,u_type) values ('John','Doe','bigjon78','password123',2);
insert into users(first_name,last_name,username,pass,u_type) values ('Janet','Dyne','waspinator23','password34',2);
insert into users(first_name,last_name,username,pass,u_type) values ('Tony','Marx','smalltone90','password99',2);
insert into users(first_name,last_name,username,pass,u_type) values ('Steve','Jacobs','gosteve72','password00',1);
insert into users(first_name,last_name,username,pass,u_type) values ('Marissa','Ruiz','ruiz68','password10',0);

-- accounts
insert into accounts(balance,a_type,status,approved_by,created_on) values (600.89,'Checking','Open','Marissa Ruiz','02-25-2006');
insert into accounts(balance,a_type,status,approved_by,created_on) values (3400.89,'Savings','Open','Marissa Ruiz','02-25-2006');

-- acctxref
insert into acctxref(user_id,account_id) values (2,1);
insert into acctxref(user_id,account_id) values (2,2);

-- information
insert into information(u_id,ssn,address,city,state,zip,phone,email) values (1,'956251456','90 South St.','Norwalk','CA','65941','7412588989','doester@gmail.com');
insert into information(u_id,ssn,address,city,state,zip,phone,email) values (2,'606558888','454 Blue Road','Ontario','TX','95899','9658451000','wasper@aol.com');

select * from users 
	left join information on
		users.u_id = information.u_id;
