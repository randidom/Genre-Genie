BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, movie_genre, movie, profile_genre, genre, profile;

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
    release_date date,
    overview varchar(500),
    vote_average numeric(3,2),
    is_favorite boolean,
    CONSTRAINT PK_movie_id PRIMARY KEY (movie_id)

);

CREATE TABLE movie_genre (
    genre_id INT,
    movie_id INT,
    CONSTRAINT FK_movie_id FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
    CONSTRAINT FK_movie_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);


CREATE TABLE profile (
    user_id int,
    profile_id SERIAL,
    name varchar(50),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT PK_profile_id PRIMARY KEY (profile_id)


);

CREATE TABLE profile_genre (
      profile_genre_id SERIAL,
      profile_id int NOT NULL,
      genre_id int NOT NULL,
      CONSTRAINT FK_profile_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id),
      CONSTRAINT FK_profile_genre_profile_id FOREIGN KEY (profile_id) REFERENCES profile(profile_id),
      CONSTRAINT PK_profile_genre_table_id PRIMARY KEY (profile_genre_id)
);

COMMIT TRANSACTION;
