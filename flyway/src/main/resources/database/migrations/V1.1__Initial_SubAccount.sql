CREATE TABLE example.subaccount (
    id VARCHAR(128) NOT NULL PRIMARY KEY,
    userName VARCHAR NOT NULL,
    pii VARCHAR NOT NULL,
    firstName VARCHAR NOT NULL,
    lastName VARCHAR NOT NULL,
    accountId VARCHAR NOT NULL
);
