DROP TYPE IF EXISTS MOVIE_GENRE CASCADE;
DROP TYPE IF EXISTS MPAA_RATING CASCADE;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS coordinates CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS movies CASCADE;
DROP TABLE IF EXISTS movie_owners CASCADE;

CREATE TYPE MOVIE_GENRE AS ENUM ('WESTERN', 'MUSICAL', 'THRILLER', 'FANTASY');
CREATE TYPE MPAA_RATING AS ENUM ('G', 'PG', 'PG_13', 'R', 'NC_17');

CREATE TABLE users (
    login TEXT PRIMARY KEY,
    password BYTEA NOT NULL,
    salt BYTEA NOT NULL
);

CREATE TABLE coordinates (
    id BIGSERIAL PRIMARY KEY,
    x INT,
    y INT
);

CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL CHECK (name != ''),
    birthday DATE NOT NULL,
    weight INT NOT NULL CHECK (weight > 0),
    passportID TEXT CHECK (7 <= length(passportID) AND length(passportID) <= 32)
);

CREATE TABLE movies (
    key BIGINT PRIMARY KEY ,
    id BIGSERIAL UNIQUE NOT NULL,
    name TEXT NOT NULL CHECK (name != ''),
    coordinatesID BIGINT REFERENCES coordinates(id) ON DELETE RESTRICT UNIQUE NOT NULL,
    creationDate TIMESTAMPTZ DEFAULT NOW() NOT NULL,
    oscarsCount BIGINT CHECK (oscarsCount > 0),
    genre MOVIE_GENRE NOT NULL,
    mpaaRating MPAA_RATING NOT NULL,
    directorID BIGINT REFERENCES person(id) ON DELETE RESTRICT UNIQUE NOT NULL
);

CREATE TABLE movie_owners (
    movieKey BIGINT REFERENCES movies(key) ON DELETE CASCADE PRIMARY KEY,
    ownerLogin TEXT DEFAULT '' references users(login) ON DELETE SET DEFAULT NOT NULL
);
