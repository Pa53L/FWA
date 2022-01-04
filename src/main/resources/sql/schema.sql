CREATE TABLE IF NOT EXISTS users(
    id              SERIAL CONSTRAINT users_pkey PRIMARY KEY,
    first_name      VARCHAR(80) NOT NULL,
    last_name       VARCHAR(80) NOT NULL,
    phone_number    VARCHAR(30) NOT NULL,
    password        VARCHAR(255) NOT NULL
);