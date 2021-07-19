create schema auth;

create table auth.users
(
    id uuid not null,
    username varchar not null,
    password varchar not null,
    firstname varchar not null,
    lastname varchar not null,
    patronymic varchar
);

create unique index users_id_uindex
    on auth.users (id);

create unique index users_login_uindex
    on auth.users (username);

alter table auth.users
    add constraint users_pk
        primary key (id);

