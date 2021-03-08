INSERT INTO `genre` (`id`, `name`, `description`) VALUES(1, 'Indie Folk', 'whiny-voiced white sadboi music');
INSERT INTO `genre` (`id`, `name`, `description`) VALUES(2, 'Indie Rock and Roll', 'straight bangers from your youth');

INSERT INTO `playlist`(`id`, `name`, `description`, `artwork`) VALUES(1, 'Sadbois', 'whiny musics', 'some url');
INSERT INTO `playlist`(`id`, `name`, `description`, `artwork`) VALUES(2, 'Happybois', 'less whiny musics', 'some other url');


INSERT INTO `artist` (`id`, `name`) VALUES(1, 'The Mountain Goats');
INSERT INTO `artist` (`id`, `name`) VALUES(2, 'We Were Promised Jetpacks');

INSERT INTO `album`(`id`, `name`, `artist_id`, `cover`, `genre_id`) VALUES(1, 'We Shall All Be Healed', 1, 'some url', 1);
INSERT INTO `album`(`id`, `name`, `artist_id`, `cover`, `genre_id`) VALUES(2, 'Tallahassee', 1, 'some other url', 1);
INSERT INTO `album`(`id`, `name`, `artist_id`, `cover`, `genre_id`) VALUES(3, 'In League With Dragons', 1, 'some other other url', 1);
INSERT INTO `album`(`id`, `name`, `artist_id`, `cover`, `genre_id`) VALUES(4, 'The Sunset Tree', 1, 'and another', 1);
INSERT INTO `album`(`id`, `name`, `artist_id`, `cover`, `genre_id`) VALUES(5, 'These Four Walls', 2, 'and again!', 2);

INSERT INTO `track` (`id`, `title`, `artist_id`, `album_id`, `duration`, `lyrics`, `genre_id`, `playlist_id`) values(1, 'Cotton', 1, 1, 360, 'This song is for the rats...', 1, 1);
INSERT INTO `track` (`id`, `title`, `artist_id`, `album_id`, `duration`, `lyrics`, `genre_id`, `playlist_id`) VALUES (2, 'Oceanographers Choice', 1, 2, 400, 'WELL...', 1, 1);
INSERT INTO `track` (`id`, `title`, `artist_id`, `album_id`, `duration`, `lyrics`, `genre_id`, `playlist_id`) VALUES (3, 'Younger', 1, 3, 500, 'Crank that siren high...', 1, 1);
INSERT INTO `track` (`id`, `title`, `artist_id`, `album_id`, `duration`, `lyrics`, `genre_id`, `playlist_id`) VALUES (4, 'Broom People', 1, 4, 360, '36 Hudson in the garage...', 1, 1);
INSERT INTO `track` (`id`, `title`, `artist_id`, `album_id`, `duration`, `lyrics`, `genre_id`, `playlist_id`) VALUES (5, 'This Is My House, This Is My Home', 2, 5, 545, 'Woke when it was dark...', 2, 2);

COMMIT;