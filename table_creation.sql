--user table
create table users(
  u_id int primary key unique,
  first_name varchar(50),
  last_name varchar(50),
  u_type int);

--account table
create table accounts(
  a_id int primary key unique,
  u_id int not null,
  balance money,
  a_type varchar(50),
  foreign key(u_id) references users(u_id));

-- login table
create table logins(
  u_id int primary key,
  username varchar(50) unique,
  password varchar(50),
  foreign key(u_id) references users(u_id));

--personal info table
create table information(
	u_id int primary key,
	ssn varchar(9),
	address varchar(50),
	city varchar(50),
	state varchar(25),
	zip varchar(25),
	phone varchar(15),
	email varchar(50),
	foreign key (u_id) references users(u_id)
);

--approvals table
create table approvals(
  trans_id int primary key unique,
  a_id int,
  approvedBy int,
  is_approved boolean,
  appr_date date,
  foreign key (a_id) references accounts(a_id),
);


-- sample values
insert into users values(49,'steve','orange',4);
select * from users;

insert into accounts values(999,49,85.78);
select * from accounts;

select * from accounts left join users on users.idnum = accounts.idnum;

insert into logins values (49,'steve90','password30');

select * from users
	left join accounts on users.idnum = accounts.idnum
	left join logins on users.idnum = logins.idnum;
