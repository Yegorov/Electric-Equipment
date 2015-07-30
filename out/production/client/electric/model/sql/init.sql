CREATE TABLE `countries` (
    `id` INTEGER,
    `country` TEXT NOT NULL UNIQUE,
    `country_code` TEXT(3) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `manufacturers` (
    `id` INTEGER,
    `title` TEXT NOT NULL,
    `id_country` INTEGER NOT NULL,
    `phone_numbers` TEXT NOT NULL,
    `emails` TEXT NOT NULL,
    `site` TEXT NOT NULL,
    `description` TEXT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`id_country`) REFERENCES `countries`(`id`) ON DELETE CASCADE
);

CREATE TABLE `transformers` (
    `id` INTEGER,
    `title` TEXT NOT NULL,
    `id_manufacturer` INTEGER NOT NULL,
    `id_category` INTEGER NOT NULL,
    `id_type` INTEGER NOT NULL,
    `id_core` INTEGER NOT NULL,
    `id_cooling` INTEGER NOT NULL,
    `id_winding_configuration` INTEGER NOT NULL,
    `id_material_winding` INTEGER NOT NULL,
    `count_winding` INTEGER NOT NULL,
    `count_phase` INTEGER NOT NULL,
    `rated_current` INTEGER NOT NULL,
    `primary_winding_voltage` INTEGER NOT NULL,
    `secondary_winding_voltage` INTEGER NOT NULL,
    `description` TEXT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`id_manufacturer`) REFERENCES `manufacturers`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_category`) REFERENCES `categories_transformers`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_type`) REFERENCES `types_transformers`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_core`) REFERENCES `cores`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_cooling`) REFERENCES `cooling_types`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_winding_configuration`) REFERENCES `winding_configuration`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_material_winding`) REFERENCES `materials`(`id`) ON DELETE CASCADE    
);

CREATE TABLE `voltage_relays` (
    `id` INTEGER,
    `title` TEXT NOT NULL,
    `id_manufacturer` INTEGER NOT NULL,
    `id_mounting` INTEGER NOT NULL,
    `count_phase` INTEGER NOT NULL,
    `number_nc_contacts` INTEGER NOT NULL,
    `number_no_contacts` INTEGER NOT NULL,
    `rated_coil_voltage` INTEGER NOT NULL,
    `rated_current` INTEGER NOT NULL,
    `max_current` INTEGER NOT NULL,
    `rated_voltage` INTEGER NOT NULL,
    `max_voltage` INTEGER NOT NULL,
    `rated_power` INTEGER NOT NULL,
    `description` TEXT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`id_manufacturer`) REFERENCES `manufacturers`(`id`) ON DELETE CASCADE,
    FOREIGN KEY(`id_mounting`) REFERENCES `types_mounting`(`id`) ON DELETE CASCADE 
);

CREATE TABLE `categories_transformers` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)    
);

CREATE TABLE `types_transformers` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)    
);

CREATE TABLE `cores` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)    
);

CREATE TABLE `cooling_types` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)    
);

CREATE TABLE `materials` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
);

CREATE TABLE `winding_configuration` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
);

CREATE TABLE `types_mounting` (
    `id` INTEGER,
    `title` TEXT NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
);


CREATE UNIQUE INDEX `countries_country_idx` ON `countries`(`country`);
CREATE INDEX `countries_country_code_idx` ON `countries`(`country_code`);
CREATE UNIQUE INDEX `categories_transformers_title_idx` ON `categories_transformers`(`title`);
CREATE UNIQUE INDEX `types_transformers_title_idx` ON `types_transformers`(`title`);
CREATE UNIQUE INDEX `cores_title_idx` ON `cores`(`title`);
CREATE UNIQUE INDEX `cooling_types_title_idx` ON `cooling_types`(`title`);
CREATE UNIQUE INDEX `materials_title_idx` ON `materials`(`title`);
CREATE UNIQUE INDEX `winding_configuration_title_idx` ON `winding_configuration`(`title`);
CREATE UNIQUE INDEX `types_mounting_title_idx` ON `types_mounting`(`title`);


INSERT INTO `categories_transformers`(`id`, `title`) VALUES(1, 'Неизвестно');
INSERT INTO `types_transformers`(`id`, `title`) VALUES(1, 'Неизвестно');
INSERT INTO `cores`(`id`, `title`) VALUES(1, 'Неизвестно');
INSERT INTO `cooling_types`(`id`, `title`) VALUES(1, 'Неизвестно');
INSERT INTO `materials`(`id`, `title`) VALUES(1, 'Неизвестно');
INSERT INTO `winding_configuration`(`id`, `title`) VALUES(1, 'Неизвестно');
INSERT INTO `types_mounting`(`id`, `title`) VALUES(1, 'Неизвестно');