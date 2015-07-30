INSERT INTO `countries`(`id`, `country`, `country_code`)
VALUES
(1, '�������', 'UA'), 
(2, '������', 'RU'), 
(3, '���������', 'CH'), 
(4, '��������', 'DE');
INSERT INTO `manufacturers` (`id`, `title`, `id_country`, `phone_numbers`, `emails`, `site`, `description`)
VALUES 
( 
    1,
    'ABB',
    3,
    '+38(044)495-22-11,+38(044)495-22-10',
    'ua-publicbox@abb.com',
    'http://abb.com/ua',
    '�������-����������� ��������, ������������������ � ������� ��������������, ��������������� �������������� � �������������� ����������.'
),
(
    2,
    'Siemens',
    4,
    '+38(044)392-23-00,+38(044)392-23-01',
    'ua.public@siemens.com',
    'http://www.siemens.com/entry/ua/ru/',
    '�������� ����������������� �������, ���������� � ������� ��������������, �����������, ��������������� ������������, ����������, ������������ ������������ � ������������, � ����� ������������������ ����� � ��������� �������� ��������������, ���������� � �����.'
);
INSERT INTO `categories_transformers`(`title`) 
VALUES
('�������������� ����������'),
('�������������� �������');

INSERT INTO `types_transformers`(`title`) 
VALUES 
('����������'), 
('����������');

INSERT INTO `cores`(`title`) 
VALUES
('��������'), 
('����������'), 
('������������');

INSERT INTO `cooling_types`(`title`) 
VALUES
('������ ��������� ����������'),
('��������'),
('�����');
INSERT INTO `materials`(`title`) 
VALUES
('����'),
('��������');
INSERT INTO `winding_configuration`(`title`) 
VALUES
('������'),
('������/�����������');
INSERT INTO `types_mounting`(`title`) 
VALUES
('� �������'),
('� ������'),
('��������'),
('�� DIN �����'),
('��������');