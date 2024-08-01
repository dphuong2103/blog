CREATE TABLE user_token(
                    id VARCHAR(36) DEFAULT (UUID_TO_BIN(UUID())),
                    user_id VARCHAR(36) NOT NULL,
                    token_type ENUM('ACCESS', 'REFRESH') NOT NULL,
                    token_value TEXT NOT NULL,
                    expiration_time TIMESTAMP NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    PRIMARY KEY (id),
                    FOREIGN KEY (user_id) REFERENCES users(id)
);
