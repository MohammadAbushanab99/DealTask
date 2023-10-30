CREATE DATABASE IF NOT EXISTS mydatabase;
USE mydatabase;

CREATE TABLE IF NOT EXISTS deals (
    dealUniqueId VARCHAR(255) PRIMARY KEY,
    fromCurrencyISOCode VARCHAR(3),
    toCurrencyISOCode VARCHAR(3),
    dealTimestamp VARCHAR(255),
    dealAmountInOrderingCurrency VARCHAR(255)
);