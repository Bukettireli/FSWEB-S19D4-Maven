CREATE SCHEMA IF NOT EXISTS fsweb;

DROP TABLE IF EXISTS actor_movie;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS actor;

CREATE TABLE IF NOT EXISTS actor (
                                     id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    gender     VARCHAR(50),
    birth_date DATE
    );

CREATE TABLE IF NOT EXISTS movie (
                                     id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name          VARCHAR(255) NOT NULL,
    director_name VARCHAR(255) NOT NULL,
    rating        INT,
    release_date  DATE
    );

CREATE TABLE IF NOT EXISTS actor_movie (
                                           actor_id BIGINT NOT NULL,
                                           movie_id BIGINT NOT NULL,
                                           PRIMARY KEY (actor_id, movie_id),
    FOREIGN KEY (actor_id) REFERENCES actor (id),
    FOREIGN KEY (movie_id) REFERENCES movie (id)
    );