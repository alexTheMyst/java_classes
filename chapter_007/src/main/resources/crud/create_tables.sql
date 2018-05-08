create table roles
(
  id   serial primary key,
  name varchar(100) unique
);

create table users
(
  id   serial PRIMARY KEY,
  name varchar(50) unique,
  role_id int references roles (id)
);

create table rules (
  id   serial primary key,
  name varchar(100) unique
);

create table role_rules (
  role_id int references roles (id),
  rule_id int references rules (id)
);

create table states
(
  id   serial primary key,
  name varchar(50) unique
);

create table categories
(
  id   serial primary key,
  name varchar(50) unique
);

create table items (
  id     serial primary key,
  header varchar(200),
  user_id int references users(id),
  state_id int references states(id),
  category_id int references categories(id)
);

create table comments
(
  id   serial primary key,
  text text,
  item_id int references items(id)
);

create table attaches
(
  id   serial primary key,
  link varchar(200),
  item_id int references items(id)
);
