CREATE TABLE credit_card (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    account VARCHAR(36) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    closing_day INT NOT NULL,
    due_day INT NOT NULL,
    credit_limit BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE invoice (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    credit_card VARCHAR(36) NOT NULL,
    amount BIGINT NULL DEFAULT 0,
    due_date DATE NOT NULL,
    effective BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

ALTER TABLE post_item
    ADD COLUMN invoice VARCHAR(36) NULL;