create schema participant;

create table if not exists participant.participants
(
    id uuid not null
    constraint table_name_pk
    primary key,
    "firstname" varchar not null,
    "lastname" varchar not null
);

alter table participant.participants owner to "Admin";

create unique index if not exists table_name_id_uindex
	on participant.participants (id);

