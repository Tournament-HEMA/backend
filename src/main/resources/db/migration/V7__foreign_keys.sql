
alter table nomination.nominations
    add constraint nominations_tournaments_tournament_id_fk
        foreign key (tournament_id) references tournament.tournaments
            on update cascade on delete cascade;

alter table round.rounds
    add constraint rounds_nominations_nomination_id_fk
        foreign key (nomination_id) references nomination.nominations
            on update cascade on delete cascade;

alter table duel.duels
    add constraint duels_rounds_round_id_fk
        foreign key (round_id) references round.rounds
            on update cascade on delete cascade;

alter table duel.duels
    add constraint duels_participants_hemaratings_id1_fk
        foreign key (first_opponent) references participant.participants
            on update cascade on delete cascade;

alter table duel.duels
    add constraint duels_participants_hemaratings_id2_fk
        foreign key (second_opponent) references participant.participants
            on update cascade on delete cascade;
