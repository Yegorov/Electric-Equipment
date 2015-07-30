INSERT INTO `countries`(`id`, `country`, `country_code`)
VALUES
(1, 'Украина', 'UA'), 
(2, 'Россия', 'RU'), 
(3, 'Швейцария', 'CH'), 
(4, 'Германия', 'DE');
INSERT INTO `manufacturers` (`id`, `title`, `id_country`, `phone_numbers`, `emails`, `site`, `description`)
VALUES 
( 
    1,
    'ABB',
    3,
    '+38(044)495-22-11,+38(044)495-22-10',
    'ua-publicbox@abb.com',
    'http://abb.com/ua',
    'Шведско-швейцарская компания, специализирующаяся в области электротехники, энергетического машиностроения и информационных технологий.'
),
(
    2,
    'Siemens',
    4,
    '+38(044)392-23-00,+38(044)392-23-01',
    'ua.public@siemens.com',
    'http://www.siemens.com/entry/ua/ru/',
    'Немецкий транснациональный концерн, работающий в области электротехники, электроники, энергетического оборудования, транспорта, медицинского оборудования и светотехники, а также специализированных услуг в различных областях промышленности, транспорта и связи.'
);
INSERT INTO `categories_transformers`(`title`) 
VALUES
('Трансформаторы напряжения'),
('Трансформаторы силовые');

INSERT INTO `types_transformers`(`title`) 
VALUES 
('Повышающий'), 
('Понижающий');

INSERT INTO `cores`(`title`) 
VALUES
('Броневое'), 
('Стержневое'), 
('Тороидальное');

INSERT INTO `cooling_types`(`title`) 
VALUES
('Жидкий негорючий диэлектрик'),
('Масляный'),
('Сухой');
INSERT INTO `materials`(`title`) 
VALUES
('Медь'),
('Алюминий');
INSERT INTO `winding_configuration`(`title`) 
VALUES
('Звезда'),
('Звезда/Треугольник');
INSERT INTO `types_mounting`(`title`) 
VALUES
('В розетку'),
('В стойку'),
('Винтовой'),
('На DIN рейку'),
('Навесной');