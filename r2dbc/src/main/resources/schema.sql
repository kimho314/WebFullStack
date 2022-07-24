DROP TABLE IF EXISTS person;
CREATE TABLE person(
    id SERIAL PRIMARY KEY,
    firstname varchar(255),
    lastname varchar(255)
);