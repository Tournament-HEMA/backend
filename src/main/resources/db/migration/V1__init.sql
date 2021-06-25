create table participant.table_name
(
    id uuid not null,
    "firstName" varchar not null,
    "lastName" varchar not null
);

create unique index table_name_id_uindex
	on participant.table_name (id);

alter table participant.table_name
    add constraint table_name_pk
        primary key (id);