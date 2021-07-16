create table auth.users_roles
(
    id uuid not null,
    user_id uuid not null
        constraint users_roles_users_id_fk
            references auth.users
            on update cascade on delete cascade,
    role_id uuid not null
        constraint users_roles_roles_id_fk
            references auth.roles
            on update cascade on delete cascade
);

create unique index users_roles_id_uindex
    on auth.users_roles (id);

alter table auth.users_roles
    add constraint users_roles_pk
        primary key (id);

create unique index users_roles_user_id_role_id_uindex
    on auth.users_roles (user_id, role_id);

