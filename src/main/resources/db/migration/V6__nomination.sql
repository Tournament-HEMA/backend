create table if not exists nomination.nominations
(
    nomination_id uuid not null,
    tournament_id uuid not null,
    category varchar,
    name varchar
);

create unique index nominations_nomination_id_uindex
    on nomination.nominations (nomination_id);

alter table nomination.nominations
    add constraint nominations_pk
        primary key (nomination_id);