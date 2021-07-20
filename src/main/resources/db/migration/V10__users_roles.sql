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

INSERT INTO auth.users (id, username, password, firstname, lastname, patronymic) VALUES ('3df823ff-a995-42b6-ab71-bc7ecfeca418', 'admin', '$2a$10$wsLD/Pmk92f6YdDm8Cp8S.C02MDwiCTNI6g2FKwxnDmB.OF/yY1OC', 'pawel', 'krouch', 'milohin');
INSERT INTO auth.roles (id, name) VALUES ('16d97fa0-459a-48ea-ba08-5a3833a9d524', 'USER');
INSERT INTO auth.roles (id, name) VALUES ('75fdfd98-8567-4494-a964-68da48e1f68c', 'ADMIN');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('cdeaf651-2e45-42b4-821f-b0cde2ca12a9', '3df823ff-a995-42b6-ab71-bc7ecfeca418', '75fdfd98-8567-4494-a964-68da48e1f68c');
