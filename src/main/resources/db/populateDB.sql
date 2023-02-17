DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'user breakfast', 500, 100000),
       ('2020-01-30 13:00:00', 'user lunch', 1000, 100000),
       ('2020-01-30 20:00:00', 'user dinner', 500, 100000),
       ('2020-01-31 0:00:00', 'user cheat-mil', 100, 100000),
       ('2020-01-31 10:00:00', 'user breakfast', 1000, 100000),
       ('2020-01-31 13:00:00', 'user lunch', 500, 100000),
       ('2020-01-31 20:00:00', 'user dinner', 410, 100000),
       ('2020-01-30 10:00:00', 'admin breakfast', 500, 100001),
       ('2020-01-30 13:00:00', 'admin lunch', 500, 100001);
