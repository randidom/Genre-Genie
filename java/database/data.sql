BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


INSERT INTO genre (genre_id, genre_name) VALUES (28, 'Action'), (12, 'Adventure'), (16, 'Animation'), (35, 'Comedy'),(80, 'Crime'), (99, 'Documentary'),
(18, 'Drama'),(10751, 'Family'),(14, 'Fantasy'),(36, 'History'), (27, 'Horror'), (10402, 'Music'), (9648, 'Mystery'), (10749, 'Romance'), (878, 'Science Fiction'),
(10770, 'TV Movie'), (53, 'Thriller'), (10752, 'War'), (37, 'Western');

INSERT INTO profile (user_id, name) VALUES (1, 'test');
INSERT INTO profile_genre(profile_id, genre_id) VALUES (1, 28), (1, 12), (1, 35);


COMMIT TRANSACTION;
