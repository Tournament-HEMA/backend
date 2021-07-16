create table auth.roles
(
    id uuid not null,
    name varchar not null
);

create unique index roles_id_uindex
    on auth.roles (id);

create unique index roles_name_uindex
    on auth.roles (name);

alter table auth.roles
    add constraint roles_pk
        primary key (id);
