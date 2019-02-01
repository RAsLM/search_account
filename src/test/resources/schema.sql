DROP TABLE IF EXISTS user_account;

CREATE TABLE IF NOT EXISTS user_account (
  user_account_id serial not null primary key,
  login varchar(50) not null unique,
  first_name varchar(50) not null,
  middle_name varchar (50) not null,
  last_name varchar (50)
);

INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login1', 'first_name1', 'middle_name1', 'last_name1');
INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login2', 'first_name2', 'middle_name2', 'last_name2');
INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login3', 'first_name3', 'middle_name3', 'last_name3');
INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login4', 'first_name4', 'middle_name4', 'last_name4');
INSERT INTO user_account (login, first_name, middle_name, last_name) VALUES ('login5', 'first_name5', 'middle_name5', 'last_name5');