create table if not exists tournament.tournaments
(
    tournament_id uuid not null,
    name varchar not null,
    beginning date,
    ending date
);

create unique index tournaments_name_uindex
    on tournament.tournaments (name);

create unique index tournaments_tournament_id_uindex
    on tournament.tournaments (tournament_id);

alter table tournament.tournaments
    add constraint tournaments_pk
        primary key (tournament_id);