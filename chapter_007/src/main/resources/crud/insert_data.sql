insert into roles (name) values ('administrator');
insert into roles (name) values ('user');

insert into rules (name) values ('create');
insert into rules (name) values ('update');
insert into rules (name) values ('delete');

insert into role_rules
select role.id, rule.id from roles role, rules rule where role.name = 'user' and rule.name = 'create';

insert into role_rules
select role.id, rule.id from roles role, rules rule where role.name = 'user' and rule.name = 'update';

insert into role_rules
select role.id, rule.id from roles role, rules rule where role.name = 'administrator' and rule.name = 'create';

insert into role_rules
select role.id, rule.id from roles role, rules rule where role.name = 'administrator' and rule.name = 'update';

insert into role_rules
select role.id, rule.id from roles role, rules rule where role.name = 'administrator' and rule.name = 'delete';

insert into users (name, role_id) select 'admin', id from roles where name = 'administrator';

insert into users (name, role_id) select 'user01', id from roles where name = 'user';

insert into users (name, role_id) select 'user02', id from roles where name = 'user';

insert into states (name) values ('new');

insert into states (name) values ('in progress');

insert into states (name) values ('finished');

insert into categories (name) values ('new feature');

insert into categories (name) values ('bug fix');

do $$
declare
user_id integer;
state_id integer;
category_id integer;
begin
-- first item
select id into user_id from users where name = 'user01';
select id into state_id from states where name = 'new';
select id into category_id from categories where name = 'new feature';
insert into items (header, user_id, state_id, category_id) values ('feature001', user_id, state_id, category_id);
-- second item
select id into user_id from users where name = 'user02';
select id into state_id from states where name = 'new';
select id into category_id from categories where name = 'bug fix';
insert into items (header, user_id, state_id, category_id) values ('bug001', user_id, state_id, category_id);
end $$;

insert into comments (text, item_id)
select 'Starting new feature', id from items where header = 'feature001';

insert into comments (text, item_id)
select 'Work in progress', id from items where header = 'feature001';

insert into comments (text, item_id)
select 'We need to fix a bug. Description in attachment', id from items where header = 'bug001';

insert into attaches (link, item_id)
select '//server/attachments/attach01.txt', id from items where header = 'bug001';