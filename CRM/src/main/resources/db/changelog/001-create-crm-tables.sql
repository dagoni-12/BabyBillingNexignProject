-- db.changelog-master.sql
-- Liquibase SQL-based master changelog

-- changeset crm:001-create-managers
CREATE TABLE crm_manager (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

INSERT INTO crm_manager (name, password) VALUES ('admin', '1234');

CREATE TABLE crm_users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    msisdn VARCHAR(20) NOT NULL UNIQUE,
    tariff_id INTEGER NOT NULL,
    balance NUMERIC(10,2) DEFAULT 100.00
);

-- Тестовые абоненты
INSERT INTO crm_users (full_name, msisdn, tariff_id, balance) VALUES
('Иван Абонент', '+79111234567', 1, 100.00),
('Ольга Абонент', '+79111234568', 2, 150.00);
