CREATE DATABASE  IF NOT EXISTS `supplier_tracker`;            

USE `supplier_tracker`;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `price` DECIMAL(10,2) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
);

INSERT INTO products VALUES (1, 'tomatoes', 'vegetables', 0.90);
INSERT INTO products VALUES (2, 'potatoes', 'vegetables', 0.50);
INSERT INTO products VALUES (3, 'onions', 'vegetables', 0.3);
INSERT INTO products VALUES (4, 'milk', 'dairy', 1.00);
INSERT INTO products VALUES (5, 'cheese', 'dairy', 6.00);
INSERT INTO products VALUES (6, 'butter', 'dairy', 8.00);

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `contact_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`supplier_id`),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO suppliers VALUES (1, 'company1', 'petros', 'petros@email.com', 1);
INSERT INTO suppliers VALUES (2, 'company2', 'mihalis', 'mihalis@email.com', 3);
INSERT INTO suppliers VALUES (3, 'company3', 'nikos', 'nikos@email.com', 4);
INSERT INTO suppliers VALUES (4, 'company4', 'giorgos', 'giorgos@email.com', 2);
INSERT INTO suppliers VALUES (5, 'company5', 'eleni', 'eleni@email.com', 5);
INSERT INTO suppliers VALUES (6, 'company6', 'maria', 'maria@email.com', 6);
