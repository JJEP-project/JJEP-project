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
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.columns WHERE table_name = 'users' AND column_name = 'roles' AND data_type = 'character varying';
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

-- changeset entl:create-user-application-table
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'user_application';

CREATE TABLE user_application (
      id BIGSERIAL,
      user_id BIGSERIAL NOT NULL ,
      is_blood_protection BOOLEAN NOT NULL DEFAULT FALSE,
      is_generational_iht BOOLEAN NOT NULL DEFAULT FALSE,
      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      notes TEXT,
      primary_property FLOAT NOT NULL DEFAULT 0,
      UK_holiday_home FLOAT NOT NULL DEFAULT 0,
      BTL_property FLOAT NOT NULL DEFAULT 0,
      foreign_property FLOAT NOT NULL DEFAULT 0,
      foreign_will BOOLEAN NOT NULL DEFAULT FALSE,
      investment FLOAT NOT NULL DEFAULT 0,
      savings_cash FLOAT NOT NULL DEFAULT 0,
      total FLOAT GENERATED ALWAYS AS (
                 primary_property + UK_holiday_home + BTL_property + foreign_property +
                 investment + savings_cash
             ) STORED,
      personal_life_cover FLOAT NOT NULL DEFAULT 0,
      trust BOOLEAN NOT NULL DEFAULT FALSE,
      pensions FLOAT NOT NULL DEFAULT 0,
      PRIMARY KEY (id),
      FOREIGN KEY (user_id) REFERENCES users (id)
);

--rollback DROP TABLE user_application;

-- liquibase formatted sql

