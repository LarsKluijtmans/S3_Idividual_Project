CREATE TABLE user(
    id   int     NOT NULL AUTO_INCREMENT,
    username char(50) NOT NULL,
    password char(250) NOT NULL,
    position enum('ADMIN', 'NORMAL'),
    firstname char(50) NOT NULL,
    lastname char(50) NOT NULL,
    phonenumber char(20) NOT NULL,
    email char(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (phonenumber),
    UNIQUE (email)
);

