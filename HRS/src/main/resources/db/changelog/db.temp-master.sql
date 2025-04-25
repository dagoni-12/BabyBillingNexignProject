-- db.temp-master.sql
-- Liquibase SQL-based master changelog

-- changeset anger:001-create-tariff_type
CREATE TABLE tariff_type (
tariff_type_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL
);

-- changeset anger:002-create-call_type
CREATE TABLE call_type (
call_type_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
cost_per_minute DECIMAL NOT NULL CHECK (cost_per_minute >= 0)
);

-- changeset anger:003-create-parameters
CREATE TABLE parameters (
parameter_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
unit VARCHAR(15),
data_type VARCHAR(20) NOT NULL
);

-- changeset anger:004-create-tariff
CREATE TABLE tariff (
tariff_id SERIAL PRIMARY KEY,
tariff_type_id INTEGER NOT NULL REFERENCES tariff_type(tariff_type_id),
call_type_id INTEGER NOT NULL REFERENCES call_type(call_type_id),
parameter_id INTEGER NOT NULL REFERENCES parameters(parameter_id),
tariff_name VARCHAR(50) NOT NULL,
description TEXT,
included_minutes INTEGER NOT NULL CHECK (included_minutes >= 0),
base_value INTEGER NOT NULL CHECK (base_value >= 0)
);

-- changeset anger:005-create-tariff_parameters
CREATE TABLE tariff_parameters (
tariff_parameter_id SERIAL PRIMARY KEY,
parameter_id INTEGER NOT NULL REFERENCES parameters(parameter_id),
value VARCHAR(50) NOT NULL
);

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
