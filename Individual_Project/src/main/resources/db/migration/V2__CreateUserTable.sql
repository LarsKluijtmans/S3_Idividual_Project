CREATE TABLE user(
    id   int     NOT NULL AUTO_INCREMENT,
    username char(50) NOT NULL,
    password char(50) NOT NULL,
    position enum('ADMIN', 'NORMAL'),
    firstName char(50) NOT NULL,
    lastName char(50) NOT NULL,
    phoneNumber char(20) NOT NULL,
    email char(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (phoneNumber),
    UNIQUE (email)
);

