-- liquibase formatted sql
-- changeset anger:001-create-tariff_type
CREATE TABLE tariff_type (
tariff_type_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL
);

-- changeset anger:002-create-call_type
CREATE TABLE call_type (
call_type_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
cost_per_minute INTEGER NOT NULL CHECK (cost_per_minute > 0)
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