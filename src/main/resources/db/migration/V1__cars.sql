CREATE TABLE `cars` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                        `condition_ot_the_vehicle` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                        `engine_type` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                        `kilometerage` INT(11) NULL DEFAULT NULL,
                        `car_brand` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                        `car_model` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                        `number_of_seats` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                        `price_tag` INT(11) NULL DEFAULT NULL,
                        `production_year` INT(11) NULL DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='utf8_general_ci'
ENGINE=InnoDB
;