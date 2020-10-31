CREATE TABLE users_permission
(
    user_id integer NOT NULL,
    permission varchar(100),
    album_id integer,
    PRIMARY KEY (user_id)
);