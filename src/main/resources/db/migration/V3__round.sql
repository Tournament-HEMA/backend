create table if not exists round.rounds
(
    round_id uuid not null,
    nomination_id uuid not null,
    number int,
    name varchar default 'Раунд'
);

create unique index rounds_round_id_uindex
    on round.rounds (round_id);

alter table round.rounds
    add constraint rounds_pk
        primary key (round_id);