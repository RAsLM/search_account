DROP TABLE IF EXISTS user_account;

CREATE TABLE IF NOT EXISTS user_account (
  user_account_id serial not null primary key,
  login varchar(50) not null unique,
  first_name varchar(50) not null,
  middle_name varchar (50) not null,
  last_name varchar (50)
);