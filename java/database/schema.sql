BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, movie_genre, movie, user_genre, genre;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	is_activated boolean DEFAULT false,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE genre (
    genre_id SERIAL,
    genre_name varchar(50),
    CONSTRAINT PK_genre_id PRIMARY KEY (genre_id)
);


CREATE TABLE movie (
    movie_id int,
    title varchar(100) NOT NULL,
    release_date varchar (80),
    overview varchar(1000),
    vote_average numeric(3,2),
    poster_path varchar(200),
    is_favorite boolean,
    user_id int,
    CONSTRAINT PK_movie_id PRIMARY KEY (movie_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE TABLE movie_genre (
    genre_id INT,
    movie_id INT,
    CONSTRAINT FK_movie_id FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
    CONSTRAINT FK_movie_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);


CREATE TABLE user_genre (
      user_id int NOT NULL,
      genre_id int NOT NULL,
      CONSTRAINT FK_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id),
      CONSTRAINT FK_user_genre_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;
