CREATE TABLE bankAccounts(
                             id   int    NOT NULL AUTO_INCREMENT,
                             user_id   int     NOT NULL,
                             cardNumber char(50) NOT NULL,
                             default_ boolean,
                             PRIMARY KEY (id)
);
CREATE TABLE user(
                     id   int     NOT NULL AUTO_INCREMENT,
                     username char(50) NOT NULL,
                     password varchar(50),
                     position enum('NORMAL','ADMIN'),
                     firstname varchar(50),
                     lastname varchar(50),
                     phonenumber long,
                     email varchar(50),
                     genre varchar(30),
                     sold boolean,
                     product_type varchar(50),
                     banks int,
                     products_bought int,
                     products_sold int,
                     products_favored int,
                     PRIMARY KEY (id),
                     UNIQUE (username) ,
                     FOREIGN KEY (banks) REFERENCES bankAccounts (id)
);

CREATE TABLE product_Selling(
                                user_id int NOT NULL,
                                product_Id int NOT NULL,
                                PRIMARY KEY(user_id, product_Id),
                                FOREIGN KEY(user_id) REFERENCES user(id),
                                FOREIGN KEY(product_Id) REFERENCES product(id)
);
CREATE TABLE product_Bought(
                               user_id int NOT NULL,
                               product_Id int NOT NULL,
                               PRIMARY KEY(user_id, product_Id),
                               FOREIGN KEY(user_id) REFERENCES user(id),
                               FOREIGN KEY(product_Id) REFERENCES product(id)
);
CREATE TABLE product_Favored(
                                id int NOT NULL AUTO_INCREMENT,
                                user_id int NOT NULL,
                                product_Id int NOT NULL,
                                PRIMARY KEY(id),
                                FOREIGN KEY(user_id) REFERENCES user(id),
                                FOREIGN KEY(product_Id) REFERENCES product(id)
);