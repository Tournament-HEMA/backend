create table if not exists duel.duels
(
    duel_id uuid not null,
    round_id uuid not null,
    number int,
    first_opponent uuid,
    second_opponent uuid,
    first_opponent_points int default 0,
    second_opponent_points int default 0
);

create unique index duels_duel_id_uindex
    on duel.duels (duel_id);

alter table duel.duels
    add constraint duels_pk
        primary key (duel_id);