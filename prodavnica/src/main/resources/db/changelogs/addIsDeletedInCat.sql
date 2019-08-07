ALTER TABLE `prodavnica`.`category`
    ADD COLUMN `is_deleted` BIT(1) NULL AFTER `name`;