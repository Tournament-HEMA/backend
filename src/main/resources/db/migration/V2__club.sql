create table if not exists club.clubs
(
    club_id uuid not null,
    clubname varchar not null,
    city varchar,
    active boolean default true not null
);

create unique index clubs_club_id_uindex
    on club.clubs (club_id);

create unique index clubs_clubname_uindex
    on club.clubs (clubname);

alter table club.clubs
    add constraint clubs_pk
        primary key (club_id);

-- alter table club.clubs owner to "Admin";