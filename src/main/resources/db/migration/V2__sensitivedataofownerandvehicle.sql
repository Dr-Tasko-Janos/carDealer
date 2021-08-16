CREATE TABLE `sensitivedataofownerandvehicle` (
                                                  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                  `owner_first_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                                                  `owner_last_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                                                  `owner_phone_number` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                                                  `vehicle_identification_number` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                                                  `car_id` BIGINT(20) NULL DEFAULT NULL,
                                                  PRIMARY KEY (`id`) USING BTREE,
                                                  INDEX `FKacermsghemrpqiqwodqv3l0ol` (`car_id`) USING BTREE,
                                                  CONSTRAINT `FKacermsghemrpqiqwodqv3l0ol` FOREIGN KEY (`car_id`) REFERENCES `vizsgaremek`.`cars` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
    COLLATE='utf8_general_ci'
ENGINE=InnoDB
;