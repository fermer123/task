--changeset V2_insert_data:1
SET search_path TO tasklist;

TRUNCATE TABLE users_roles, users_tasks, tasks, users RESTART IDENTITY CASCADE;

INSERT INTO users (name, username, password)
VALUES ('John Doe', 'john@mail.ru', '$2a$10$MZagFd5.nZAqUySu7QQRXey7DblYyr2G0xnFk.tYcbuScZgWvVjw6'),
       ('Mike Smt', 'mike_smt@mail.com', '$2a$10$MZagFd5.nZAqUySu7QQRXey7DblYyr2G0xnFk.tYcbuScZgWvVjw6'),
       ('Anna Smith', 'anna@gmail.com', '$2a$10$MZagFd5.nZAqUySu7QQRXey7DblYyr2G0xnFk.tYcbuScZgWvVjw6');

INSERT INTO tasks (title, description, status, expiration_date)
VALUES ('Buy cheese', 'Go to the shop and buy some Cheddar', 'TODO', '2026-05-01 12:00:00'),
       ('Finish project', 'Complete the Spring Boot tasklist app', 'IN_PROGRESS', '2026-06-15 18:00:00'),
       ('Gym workout', 'Leg day with trainer', 'DONE', '2026-04-25 10:00:00'),
       ('Read a book', 'Finish "Clean Code" by Robert Martin', 'TODO', '2026-04-25 10:00:00');

INSERT INTO users_tasks (user_id, task_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4);

INSERT INTO users_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_USER');