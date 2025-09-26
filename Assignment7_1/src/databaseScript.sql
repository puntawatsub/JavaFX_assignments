DROP DATABASE IF EXISTS currency;
CREATE DATABASE currency;
USE currency;
CREATE TABLE CURRENCY (
    id INT NOT NULL AUTO_INCREMENT,
    abbreviation VARCHAR(3) NOT NULL,
    currency_name VARCHAR(50) NOT NULL,
    val_usd DECIMAL(15, 5) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO CURRENCY (abbreviation, currency_name, val_usd) VALUES
    ('USD', 'US Dollars', 1),
    ('EUR', 'Euro', 1.17),
    ('JPY', 'Japanese Yen', 0.0067),
    ('GBP', 'Pound Sterling', 1.34),
    ('AUD', 'Australian Dollars', 0.65),
    ('CAD', 'Canadian Dollars', 0.72),
    ('CHF', 'Swiss Franc', 1.25),
    ('CNY', 'Chinese Yuan', 0.14);

DROP USER IF EXISTS 'appuser'@'localhost';
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT ON currency.CURRENCY TO 'appuser'@'localhost';