ALTER TABLE product
    ADD COLUMN seller int,
    ADD FOREIGN KEY (Seller) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE;



INSERT INTO user (`id`, `username`, `password`, `position`, `firstname`, `lastname`, `phonenumber`, `email`) VALUES ('1', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN');
INSERT INTO user (`id`, `username`, `password`, `position`, `firstname`, `lastname`, `phonenumber`, `email`) VALUES ('2', 'NORMAL', 'NORMAL', 'NORMAL', 'NORMAL', 'NORMAL', '111222', 'NORMAL');
INSERT INTO user (`id`, `username`, `password`, `position`, `firstname`, `lastname`, `phonenumber`, `email`) VALUES ('3', 'lars', 'lars', 'NORMAL', 'lars', 'lars', '222333', 'lars@normal.com');

INSERT INTO product (`id`, `title`, `sub_title`, `series`, `year`, `price`, `condition_`, `description`, `genre`, `sold`, `product_type`, `seller`) VALUES ('1', 'Pokemon platinum', 'Pokemon', 'Pokemon', '2000', '10.10', 'GOOD', 'Pokemon platinum', '59', '0', 'GAME', '2');
INSERT INTO product (`id`, `title`, `sub_title`, `series`, `year`, `price`, `condition_`, `description`, `genre`, `sold`, `product_type`, `seller`) VALUES ('3', 'Pokemon gold', 'Pokemon gold', 'Pokemon', '2001', '12.20', 'OK', 'Pokemon', '59', '0', 'GAME', '2');
INSERT INTO product (`id`, `title`, `sub_title`, `series`, `year`, `price`, `condition_`, `description`, `genre`, `sold`, `product_type`, `seller`) VALUES ('2', 'Pokemon silver', 'PoPokemon Pokemon silver', 'Pokemon', '2020', '20.50', 'TRASH', 'Pokemon silver', '59', '0', 'GAME', '2');
