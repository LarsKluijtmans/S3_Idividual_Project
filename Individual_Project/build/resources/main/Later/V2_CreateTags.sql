CREATE TABLE tags(
                     name varchar(20) NOT NULL,
                     searches int NOT NULL,
                     productsWithTag int NOT NULL,
                     PRIMARY KEY (name),
                     unique(name)
);

CREATE TABLE product_Tags(
                             id int NOT NULL AUTO_INCREMENT,
                             tag varchar(20) NOT NULL,
                             product_Id int NOT NULL,
                             PRIMARY KEY(id),
                             FOREIGN KEY(tag) REFERENCES tags(name),
                             FOREIGN KEY(product_Id) REFERENCES product(id)
);



