insert into cars (mark, model, manufactureyear)
values ('huyndai', 'i20', 2001),
       ('toyota', 'mark-II', 1993),
       ('lada', '2101', 2023),
       ('lamborgini', 'muercilado', 2019);


insert into clients_messages(messages_id, client_id)
values (2, 1);

insert into users (email, password)
values ('nonuser@mail.com', '$2a$10$Ty7n8WEHQ8dSFCIy6qb8r.rajh9qTTZteO8Uovj3Gzwri.f0Mur6m');
insert into user_role(user_id, roles)
values (1, 'ADMIN');

insert into clients (email, name, phone, car_id)
values ('pupa@email.com', 'pupa', '+795271231', 1),
       ('loma@email.com', 'loma', '+712093812', 2),
       ('spam@email.com', 'spam', '+712412551', 3);