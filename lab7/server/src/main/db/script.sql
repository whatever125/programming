create table users (
    login varchar(255) primary key,
    password varchar(255) not null,
    salt varchar(255) not null
);