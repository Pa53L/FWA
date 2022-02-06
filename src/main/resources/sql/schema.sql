CREATE TABLE IF NOT EXISTS users(
    id              SERIAL CONSTRAINT users_pkey PRIMARY KEY,
    first_name      VARCHAR(80) NOT NULL unique ,
    last_name       VARCHAR(80) NOT NULL,
    phone_number    VARCHAR(30) NOT NULL unique,
    password        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS auth(
    id              SERIAL CONSTRAINT auth_history_pkey PRIMARY KEY,
    user_id INTEGER NOT NULL,
    type            VARCHAR(80) NOT NULL,
    address         VARCHAR(80) NOT NULL,
    time            VARCHAR(30) NOT NULL,
    CONSTRAINT fk_user_id
        FOREIGN KEY(user_id)
            REFERENCES users(id)
);

