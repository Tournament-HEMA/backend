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

------------------------------------------------------------

INSERT INTO auth.users (id, username, password, firstname, lastname, patronymic) VALUES ('0868961a-4d73-4d76-bc86-20227c371de9', 'user1', '12345', 'pawel', 'krouch', 'milohin');
INSERT INTO auth.users (id, username, password, firstname, lastname, patronymic) VALUES ('115890c9-c06d-41be-807b-82766b7bb0a8', 'user2', '12345', 'pawel', 'krouch', 'milohin');
INSERT INTO auth.users (id, username, password, firstname, lastname, patronymic) VALUES ('101fe082-2d9e-4eea-89ba-26c920a45ef2', 'user3', '12345', 'pawel', 'krouch', 'milohin');
INSERT INTO auth.users (id, username, password, firstname, lastname, patronymic) VALUES ('3df823ff-a995-42b6-ab71-bc7ecfeca418', 'user4', '12345', 'pawel', 'krouch', 'milohin');

INSERT INTO auth.roles (id, name) VALUES ('16d97fa0-459a-48ea-ba08-5a3833a9d524', 'USER');
INSERT INTO auth.roles (id, name) VALUES ('75fdfd98-8567-4494-a964-68da48e1f68c', 'ADMIN');

INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('036715bf-2591-4193-920e-a9e55c030eba', '0868961a-4d73-4d76-bc86-20227c371de9', '16d97fa0-459a-48ea-ba08-5a3833a9d524');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('c67062d7-8b63-407c-9230-42cddf3c9e86', '0868961a-4d73-4d76-bc86-20227c371de9', '75fdfd98-8567-4494-a964-68da48e1f68c');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('16cc1bb4-9eb0-40c7-966c-1eee09b23bb4', '115890c9-c06d-41be-807b-82766b7bb0a8', '16d97fa0-459a-48ea-ba08-5a3833a9d524');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('4033f016-a1d2-4661-8b74-3cda6e63a1d5', '115890c9-c06d-41be-807b-82766b7bb0a8', '75fdfd98-8567-4494-a964-68da48e1f68c');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('4ed17333-42a8-4ca6-8c9d-32fb7bea3ee0', '101fe082-2d9e-4eea-89ba-26c920a45ef2', '16d97fa0-459a-48ea-ba08-5a3833a9d524');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('b18e502f-9060-4b78-a7ab-272491af9525', '101fe082-2d9e-4eea-89ba-26c920a45ef2', '75fdfd98-8567-4494-a964-68da48e1f68c');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('6c80e6cb-8d40-4e8d-9940-f912f4e5fb86', '3df823ff-a995-42b6-ab71-bc7ecfeca418', '16d97fa0-459a-48ea-ba08-5a3833a9d524');
INSERT INTO auth.users_roles (id, user_id, role_id) VALUES ('cdeaf651-2e45-42b4-821f-b0cde2ca12a9', '3df823ff-a995-42b6-ab71-bc7ecfeca418', '75fdfd98-8567-4494-a964-68da48e1f68c');
