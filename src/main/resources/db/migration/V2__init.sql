
alter table participant.participants
    add number serial not null;

create unique index participants_number_uindex
	on participant.participants (number);

INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('ff3e55bd-bb53-4416-8dfb-f623bdb11706', 'Александр', 'Петров');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('c5713ba1-b795-4722-9b78-6c6c062754f5', 'Мария', 'Коткова');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('f8f01523-41b3-4cb1-a491-9be629feda04', 'Петр', 'Локтар');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('2a2215a6-94d1-4490-aee5-6754ab593bef', 'Иван', 'Васильев');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('651490d9-fc58-42cc-9fa0-d1dc320f1667', 'Дмитрий', 'Очищенко');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('a7fc641d-98fa-4df8-9fb4-9348c32edc77', 'Дарья', 'Курбатова');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('801f8d6d-9b0d-438c-acd9-434c7edb87b6', 'Василий', 'Кокарев');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('bcf1f90e-92cb-4143-afcd-c34e8b978703', 'Михаил', 'Курбатов');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('c2378e43-32ef-46bd-ac30-0901dd1c2dc9', 'Владимир', 'Григорян');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('67b8ffee-5c45-46ba-9e81-454822e543c7', 'Георгий', 'Мышьяков');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('f8682860-01fe-4ca8-8fb9-54f087b29b3f', 'Wayne', 'Stark');
INSERT INTO participant.participants ("id", "firstname", "lastname") VALUES ('1980a5d7-f271-41f2-b213-34668a1c44d8', 'Александр', 'Петров');
