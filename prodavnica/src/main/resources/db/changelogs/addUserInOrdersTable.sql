ALTER TABLE `orders` 
ADD COLUMN `user_id` BIGINT(20) NOT NULL AFTER `order_date`,
ADD INDEX `user_id_idx` (`user_id` ASC);

ALTER TABLE `orders` 
ADD CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
