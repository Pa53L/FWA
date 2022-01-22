CREATE TABLE IF NOT EXISTS users(
    id              SERIAL CONSTRAINT users_pkey PRIMARY KEY,
    first_name      VARCHAR(80) NOT NULL unique ,
    last_name       VARCHAR(80) NOT NULL,
    phone_number    VARCHAR(30) NOT NULL,
    password        VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS auth_history(
    id              SERIAL CONSTRAINT auth_history_pkey PRIMARY KEY,
    user_first_name VARCHAR(80) NOT NULL ,
    type            VARCHAR(80) NOT NULL,
    address         VARCHAR(80) NOT NULL,
    time            VARCHAR(30) NOT NULL,
    CONSTRAINT fk_user_first_name
        FOREIGN KEY(user_first_name)
            REFERENCES users(first_name)
);

