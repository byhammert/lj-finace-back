CREATE TABLE post (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    post_category VARCHAR(36) NOT NULL,
    post_subcategory VARCHAR(36) NOT NULL,
    recurrence VARCHAR(255) NOT NULL,
    account VARCHAR(36) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    note VARCHAR(4000) NULL,
    post_type VARCHAR(255) NOT NULL,
    effective BOOLEAN NOT NULL DEFAULT FALSE,
    amount BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE post_item (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    post VARCHAR(36) NOT NULL,
    number INT NOT NULL DEFAULT 1,
    amount BIGINT NOT NULL DEFAULT 0,
    effective BOOLEAN NOT NULL DEFAULT FALSE,
    payment_date TIMESTAMP NULL,
    due_date DATE NOT NULL
);

CREATE TABLE post_category (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL
);

CREATE TABLE post_subcategory (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    post_category VARCHAR(36) NOT NULL
);