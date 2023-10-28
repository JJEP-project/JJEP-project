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
      user_id BIGINT NOT NULL,
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

-- changeset entl:update-user-application-updated-at-function splitStatements:false
CREATE OR REPLACE FUNCTION update_user_application_updated_at()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--rollback DROP FUNCTION update_user_application_updated_at();


-- changeset entl:update-user-application-updated-at-trigger
CREATE TRIGGER tr_update_user_application_updated_at
    BEFORE UPDATE ON user_application
    FOR EACH ROW
EXECUTE FUNCTION update_user_application_updated_at();

--rollback DROP TRIGGER tr_update_user_application_updated_at ON user_application;

-- changeset entl:update-users-updated-at-function splitStatements:false
CREATE OR REPLACE FUNCTION update_users_updated_at()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--rollback DROP FUNCTION update_users_updated_at();


-- changeset entl:update-users-updated-at-trigger
CREATE TRIGGER tr_update_users_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW
EXECUTE FUNCTION update_users_updated_at();

--rollback DROP TRIGGER tr_update_users_updated_at ON users

-- changeset entl:drop-pensions-column
ALTER TABLE user_application DROP COLUMN pensions;

--rollback ALTER TABLE user_application ADD COLUMN pensions FLOAT NOT NULL DEFAULT 0;

-- changeset entl:create-application-clients

CREATE TABLE application_clients (
    id BIGSERIAL,
    application_id BIGINT NOT NULL,
    family_name VARCHAR(255) NOT NULL,
    forename VARCHAR(255) NOT NULL,
    marital_status VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    is_UK_IHT BOOLEAN NOT NULL DEFAULT FALSE,
    is_spouse_NRB BOOLEAN NOT NULL DEFAULT FALSE,
    is_LPA BOOLEAN NOT NULL DEFAULT FALSE,
    primary_property FLOAT NOT NULL DEFAULT 0,
    UK_holiday_home FLOAT NOT NULL DEFAULT 0,
    BTL_property FLOAT NOT NULL DEFAULT 0,
    foreign_property FLOAT NOT NULL DEFAULT 0,
    foreign_will BOOLEAN NOT NULL DEFAULT FALSE,
    BRP_assets FLOAT NOT NULL DEFAULT 0,
    non_BRP_assets FLOAT NOT NULL DEFAULT 0,
    nature_of_business VARCHAR(255),
    investments FLOAT NOT NULL DEFAULT 0,
    savings_cash FLOAT NOT NULL DEFAULT 0,
    total FLOAT GENERATED ALWAYS AS (
                 primary_property + UK_holiday_home + BTL_property + foreign_property +
                 investments + savings_cash + BRP_assets + non_BRP_assets
             ) STORED,
    personal_life_cover FLOAT NOT NULL DEFAULT 0,
    trust BOOLEAN NOT NULL DEFAULT FALSE,
    death_benefit_trust BOOLEAN NOT NULL DEFAULT FALSE,
    death_in_service FLOAT NOT NULL DEFAULT 0,
    pension FLOAT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (application_id) REFERENCES user_application (id) ON DELETE CASCADE
);

-- rollback DROP TABLE application_clients;

-- changeset entl:create-client-children
CREATE TABLE child(
    id BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE client_children (
     client_id BIGINT NOT NULL,
     child_id BIGINT NOT NULL,
     FOREIGN KEY (client_id) REFERENCES application_clients (id) ON DELETE CASCADE,
     FOREIGN KEY (child_id) REFERENCES child (id) ON DELETE CASCADE,
     PRIMARY KEY (client_id, child_id)
);

-- rollback DROP TABLE client_children;
-- rollback DROP TABLE child;

-- liquibase formatted sql

