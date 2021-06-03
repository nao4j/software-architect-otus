CREATE TABLE auth_users (
    id         BIGSERIAL PRIMARY KEY,
    login      VARCHAR(255)  NOT NULL UNIQUE,
    password   VARCHAR(1024) NOT NULL,
    first_name VARCHAR(128)  NOT NULL,
    last_name  VARCHAR(128)  NOT NULL
)
