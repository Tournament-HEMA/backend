insert into club.clubs (club_id, clubname, city) values ('bac4dc81-9a4e-4fe3-b6d6-c670623ea95c', 'firstclub', 'Chicago');

insert into club.clubs (club_id, clubname, city) values ('f8690810-ae25-4bcc-bb8e-ec5a64d3f706', 'secondclub', 'Jercy');

insert into participant.participants (hemaratings_id, firstname, lastname, patronymic, club_id) values ('c280a862-5ad2-4287-9528-163b06e29a36', 'firstname1', 'lastname1', 'patronymic1', 'bac4dc81-9a4e-4fe3-b6d6-c670623ea95c');

insert into participant.participants (hemaratings_id, firstname, lastname, patronymic, club_id) values ('db716097-cc98-4846-9b93-08908984b742', 'firstname2', 'lastname2', 'patronymic2', 'bac4dc81-9a4e-4fe3-b6d6-c670623ea95c');

insert into participant.participants (hemaratings_id, firstname, lastname, patronymic, club_id) values ('601f591f-5ba9-467c-abec-1c10d326ff40', 'firstname3', 'lastname3', 'patronymic3', 'f8690810-ae25-4bcc-bb8e-ec5a64d3f706');

insert into participant.participants (hemaratings_id, firstname, lastname, patronymic, club_id) values ('fc8d9612-dd91-443a-bdd0-aa2a5eb13b20', 'firstname4', 'lastname4', 'patronymic4', 'f8690810-ae25-4bcc-bb8e-ec5a64d3f706');

insert into tournament.tournaments (tournament_id, name) values ('0f8852cf-b358-420b-8835-1cda74b6ef75', 'tournament1');

insert into nomination.nominations (nomination_id, tournament_id, name) values ('ebf94752-6973-4463-ad4e-fe17cd8c31d0', '0f8852cf-b358-420b-8835-1cda74b6ef75', 'nomination1');

insert into round.rounds (round_id, nomination_id, number) values ('4aab6ef6-6693-4a09-9f8f-9541b88c3310', 'ebf94752-6973-4463-ad4e-fe17cd8c31d0', 1);

insert into duel.duels (duel_id, round_id, number, first_opponent, second_opponent) values ('c5e8e7dc-3e57-4299-9568-ac011b08609a', '4aab6ef6-6693-4a09-9f8f-9541b88c3310', 1, 'c280a862-5ad2-4287-9528-163b06e29a36', '601f591f-5ba9-467c-abec-1c10d326ff40');

insert into duel.duels (duel_id, round_id, number, first_opponent, second_opponent) values ('4306b70e-aa25-4c86-9d79-e0f956087221', '4aab6ef6-6693-4a09-9f8f-9541b88c3310', 2, 'db716097-cc98-4846-9b93-08908984b742', 'fc8d9612-dd91-443a-bdd0-aa2a5eb13b20');
