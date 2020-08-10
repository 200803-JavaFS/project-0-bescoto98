--user table

create table users(idnum not null, FN varchar(50), LN varchar(50), type int, primary key(idnum));

--account table
create table accounts(acctid not null, idnum int not null, balance double, primary key (acctid), foreign key(idnum) references user(idnum));

-- login table
create table logins(idnum not null, username varchar(50), password varchar(50), foreign key (idnum) references user(idnum));

--personal info table
create table information(idnum not null, ssn int, address varchar(50), city varchar(50), state varchar(25), zip varchar(25), phoneNum varchar(25), email varchar(50), foreign key idnum references user(idnum));
