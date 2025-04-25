-- liquibase formatted sql
-- changeset anger:006-insert-tariffs

INSERT INTO tariff_type (tariff_type_id, name) VALUES
    (1, 'Классика'),
    (2, 'Помесячный');

INSERT INTO call_type (call_type_id, name, cost_per_minute) VALUES
    (1, 'OUT_ROMASHKA', 1.50),  -- 1.5 у.е. * 100 (в копейках/целых)
    (2, 'OUT_OTHER', 2.50),     -- 2.5 у.е.
    (3, 'INCOMING', 0);      -- Бесплатно

INSERT INTO parameters (parameter_id, name, unit, data_type) VALUES
    (1, 'included_minutes', 'minutes', 'integer'),
    (2, 'base_value', 'currency', 'decimal');

INSERT INTO tariff (tariff_id, tariff_type_id, call_type_id, parameter_id, tariff_name, description, included_minutes, base_value)
VALUES
    -- Тариф Классика — OUT_ROMASHKA
    (1, 1, 1, 1, 'Классика', 'Исходящие внутри сети', 0, 0),
    -- Тариф Классика — OUT_OTHER
    (2, 1, 2, 1, 'Классика', 'Исходящие на другие сети', 0, 0),
    -- Тариф Классика — Входящие
    (3, 1, 3, 1, 'Классика', 'Входящие звонки', 0, 0),
    -- Тариф Помесячный — общая запись
    (4, 2, 1, 1, 'Помесячный', '50 минут в месяц + перерасчет', 50, 100);

INSERT INTO tariff_parameters (tariff_parameter_id, parameter_id, value) VALUES
    (1, 1, '50'),
    (2, 2, '100.0');
