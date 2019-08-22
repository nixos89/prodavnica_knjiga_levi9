CREATE TABLE IF NOT EXISTS `order_item` (
  `order_item_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NOT NULL,
  `book_id` BIGINT(20) NOT NULL,
  `order_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  INDEX `fk_Order_book_idx` (`book_id` ASC),
  INDEX `fk_order_item_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_Order_book`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`book_id`),
  CONSTRAINT `fk_order_item_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION) 