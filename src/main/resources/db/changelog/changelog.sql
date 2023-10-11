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

-- changeset entl:alter-users-role-to-enum
ALTER TABLE users ADD COLUMN temp_column VARCHAR(255);

UPDATE users SET temp_column = CASE
    WHEN role = 'user' THEN 'user'
    WHEN role = 'admin' THEN 'admin'
END;

ALTER TABLE users DROP COLUMN role;

ALTER TABLE users RENAME COLUMN temp_column TO role;

--rollback ALTER TABLE users ADD COLUMN temp_column role;
--rollback
--rollback UPDATE users SET temp_column = CASE
--rollback     WHEN role = 'user' THEN 'user'::role
--rollback     WHEN role = 'admin' THEN 'admin'::role
--rollback END;
--rollback
--rollback ALTER TABLE users DROP COLUMN role;
--rollback
--rollback ALTER TABLE users RENAME COLUMN temp_column TO role;

-- liquibase formatted sql

