BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, movie, genre, profile;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE genre (
    genre_id SERIAL,
    genre_name varchar(50),
    CONSTRAINT PK_genre_id PRIMARY KEY (genre_id)
);

CREATE TABLE movie (
    movie_id SERIAL,
    title varchar(100) NOT NULL,
    director varchar(100),
    release_year int,
    genre_id int NOT NULL,
    plot varchar(500),
    actors varchar (100),
    avg_rating numeric(3,2),
    is_favorite boolean,
    CONSTRAINT PK_movie_id PRIMARY KEY (movie_id),
    CONSTRAINT FK_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);


CREATE TABLE profile (
    user_id int,
    name varchar(50),
    profile_genre_id int,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_profile_genre_id FOREIGN KEY (profile_genre_id) REFERENCES genre(genre_id)

);

COMMIT TRANSACTION;
