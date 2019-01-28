DROP TABLE IF EXISTS app_security_account;

CREATE TABLE IF NOT EXISTS app_security_account (
  app_security_account_id serial not null primary key,
  login varchar(50) not null unique,
  first_name varchar(50) not null,
  middle_name varchar (50) not null,
  last_name varchar (50)
);