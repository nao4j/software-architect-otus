CREATE SEQUENCE crud.users_id_seq
    START WITH 1
    INCREMENT BY 50
    NO CYCLE;

CREATE TABLE crud.users (
    id BIGINT NOT NULL DEFAULT nextval('crud.users_id_seq'),
    username VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(15) UNIQUE
);
