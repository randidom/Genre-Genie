BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','user4','ROLE_USER');



INSERT INTO genre (genre_id, genre_name) VALUES (1, 'Comedy');
INSERT INTO genre (genre_id, genre_name) VALUES (2, 'Action');
INSERT INTO genre (genre_id, genre_name) VALUES (3, 'Horror');



INSERT INTO movie(title, release_date, overview, vote_average, is_favorite)
VALUES ('Barbie', '2023-08-23', 'Barbie wants to become human', 9.5, false);
INSERT INTO movie(title, release_date, overview, vote_average, is_favorite)
VALUES ('Spider-man', '2022-05-09', 'Tom Holland takes the role of Spider-man', 9.8, true);
INSERT INTO movie(title, release_date, overview, vote_average, is_favorite)
VALUES ('Saw V', '2017-10-30', 'John Kramer is back', 7.2, false);

INSERT INTO movie_genre (movie_id, genre_id) VALUES (1, 1), (2, 2),(3,3);


INSERT INTO profile(user_id, name)
VALUES (1, 'Randi');
INSERT INTO profile(user_id, name)
VALUES (2, 'Jim');
INSERT INTO profile(user_id, name)
VALUES (3, 'Sara');

INSERT INTO profile_genre(profile_id, genre_id)
VALUES(1, 1), (2, 2), (3, 3);


COMMIT TRANSACTION;
