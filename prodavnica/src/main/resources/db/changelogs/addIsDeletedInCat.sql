ALTER TABLE `prodavnica`.`category`
    ADD COLUMN `isDeleted` BIT(1) NULL AFTER `name`;