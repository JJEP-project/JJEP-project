-- liquibase formatted sql

-- changeset entl:create-users-table
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'users';
CREATE TYPE role AS ENUM ('admin', 'user');

CREATE TABLE users (
    id BIGSERIAL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role role NOT NULL DEFAULT 'user',
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);
-- rollback DROP TABLE users;
-- rollback DROP TYPE role;

-- liquibase formatted sql

