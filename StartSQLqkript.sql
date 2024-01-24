CREATE DATABASE per;
USE per;

CREATE TABLE per.person
(
    id      int primary key auto_increment,
    name    varchar(30),
    surname varchar(30)
);

INSERT INTO per.person (name, surname) VALUES
       ('Benjamin','Franklin'),
       ('Mikl','Jackson'),
       ('Mikl','Jackson'),
       ('Nikola','Tesla'),
       ('Ilon','Mask'),
       ('Ilon','Mask'),
       ('Ivan','Pupkin'),
       ('Petr','Sidorov'),
       ('Sidor','Ivanov'),
       ('Egor','Petrov'),
       ('Egor','Petrov'),
       ('Donald','Trump'),
       ('Joe','Baiden');