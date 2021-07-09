create schema participant;
create schema club;
create schema duel;
create schema round;
create schema tournament;
create schema nomination;

create table if not exists participant.participants
(
    hemaratings_id uuid not null,
    number serial not null,
    firstname varchar not null,
    lastname varchar not null,
    patronymic varchar not null,
    club_id uuid
);

create unique index participants_hemaratings_id_uindex
    on participant.participants (hemaratings_id);

create unique index participants_number_uindex
    on participant.participants (number);

alter table participant.participants
    add constraint participants_pk
        primary key (hemaratings_id);