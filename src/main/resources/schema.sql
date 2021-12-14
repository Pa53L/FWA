create table users (
    id serial not null primary key,
    name varchar not null,
    last_name varchar not null,
    phone_number varchar not null,
    password varchar not null
)