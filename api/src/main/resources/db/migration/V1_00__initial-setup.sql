CREATE TABLE users (
                       id VARCHAR(36) DEFAULT (UUID_TO_BIN(UUID())),
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_time DATETIME NOT NULL,
                       last_logging_time DATETIME NOT NULL,
                       primary key(id),
                       INDEX `users_index_id` (`id`)

);

CREATE TABLE blog(
                     id VARCHAR(36) DEFAULT (UUID_TO_BIN(UUID())),
                     title VARCHAR(255) NOT NULL,
                     content TEXT NOT NULL,
                     summary TINYTEXT,
                     author_id VARCHAR(36) NOT NULL,
                     slug VARCHAR(255) NOT NULL UNIQUE,
                     meta_title varchar(100) NOT NULL,
                     is_published BOOLEAN,
                     created_at DATETIME NOT NULL,
                     updated_at DATETIME NOT NULL,
                     published_at DATETIME,
                     primary key(id),
                     FOREIGN KEY(author_id) REFERENCES users(id),
                     INDEX `blog_index_id` (`id`)
);

CREATE TABLE tag(
                    id VARCHAR(36) DEFAULT (UUID_TO_BIN(UUID())),
                    tag_name_en varchar(255) NOT NULL,
                    tag_name_vn varchar(255) NOT NULL,
                    created_at DATETIME NOT NULL,
                    primary key(id)
);

CREATE TABLE blog_tag(
                         blog_id VARCHAR(36) NOT NULL,
                         tag_id VARCHAR(36) NOT NULL,
                         FOREIGN KEY(blog_id) REFERENCES blog(id),
                         FOREIGN KEY(tag_id) REFERENCES tag(id),
                         PRIMARY KEY (blog_id, tag_id),
                         INDEX `blog_tag_index_blog_id` (`blog_id`),
                         INDEX `blog_tag_index_tag_id` (`tag_id`)
);

-- Sample data for users table
INSERT INTO users (id, first_name, last_name, email, password, created_time, last_logging_time) VALUES
                                                                                                    ('user1', 'John', 'Doe', 'john@example.com', 'password123', NOW(), NOW()),
                                                                                                    ('user2', 'Jane', 'Smith', 'jane@example.com', 'pass456', NOW(), NOW()),
                                                                                                    ('user3', 'Alice', 'Johnson', 'alice@example.com', 'qwerty', NOW(), NOW());

-- Sample data for blog table
INSERT INTO blog (id, title, content, summary, author_id, slug, meta_title, is_published, created_at, updated_at, published_at)
VALUES
    ("blog1", 'Introduction to MySQL', 'This blog covers the basics of MySQL, a popular relational database management system.', 'An introduction to MySQL.', 'user1', 'introduction-to-mysql', 'Introduction to MySQL', TRUE, NOW(), NOW(), NOW()),
    ("blog2", 'Advanced SQL Queries', 'Learn about advanced SQL queries, including subqueries, window functions, and more.', 'Advanced SQL query techniques.', 'user1', 'advanced-sql-queries', 'Advanced SQL Queries', TRUE, NOW(), NOW(), NOW()),
    ("blog3", 'Understanding SQL Joins', 'In this blog, we will explore different types of SQL joins and how to use them.', 'A guide to SQL joins.', 'user1', 'understanding-sql-joins', 'Understanding SQL Joins', TRUE, NOW(), NOW(), NOW()),
    ("blog4", 'Optimizing Database Performance', 'This blog discusses various techniques to optimize the performance of your database.', 'Tips for optimizing database performance.', 'user1', 'optimizing-database-performance', 'Optimizing Database Performance', TRUE, NOW(), NOW(), NOW()),
    ("blog5", 'Introduction to Database Indexing', 'An overview of database indexing and its importance for efficient data retrieval.', 'A guide to database indexing.', 'user1', 'introduction-to-database-indexing', 'Introduction to Database Indexing', TRUE, NOW(), NOW(), NOW()),
    ("blog6", 'Working with Stored Procedures', 'Learn how to create and use stored procedures in MySQL.', 'Using stored procedures in MySQL.', 'user1', 'working-with-stored-procedures', 'Working with Stored Procedures', TRUE, NOW(), NOW(), NOW()),
    ("blog7", 'Database Security Best Practices', 'This blog outlines best practices for ensuring the security of your database.', 'Best practices for database security.', 'user1', 'database-security-best-practices', 'Database Security Best Practices', TRUE, NOW(), NOW(), NOW()),
    ("blog8", 'Using Transactions in SQL', 'Understand the importance of transactions in SQL and how to implement them.', 'A guide to SQL transactions.', 'user1', 'using-transactions-in-sql', 'Using Transactions in SQL', TRUE, NOW(), NOW(), NOW()),
    ("blog9", 'Data Backup and Recovery Strategies', 'Learn about effective strategies for data backup and recovery.', 'Data backup and recovery strategies.', 'user1', 'data-backup-and-recovery-strategies', 'Data Backup and Recovery Strategies', TRUE, NOW(), NOW(), NOW()),
    ("blog10", 'Introduction to Database Normalization', 'This blog explains the process of database normalization and its benefits.', 'An introduction to database normalization.', 'user1', 'introduction-to-database-normalization', 'Introduction to Database Normalization', TRUE, NOW(), NOW(), NOW());

INSERT INTO blog (id, title, content, summary, author_id, slug, meta_title, is_published, created_at, updated_at, published_at)
VALUES
    ("blog11", 'NoSQL vs SQL: Understanding the Differences', 'An in-depth comparison between NoSQL and SQL databases, highlighting their differences and use cases.', 'A comparison of NoSQL and SQL databases.', 'user1', 'nosql-vs-sql', 'NoSQL vs SQL: Understanding the Differences', TRUE, NOW(), NOW(), NOW()),
    ("blog12", 'Migrating Databases: Best Practices', 'This blog covers best practices for migrating databases from one platform to another.', 'Best practices for database migration.', 'user1', 'migrating-databases', 'Migrating Databases: Best Practices', TRUE, NOW(), NOW(), NOW()),
    ("blog13", 'Introduction to Database Sharding', 'An overview of database sharding and how it helps in scaling databases.', 'A guide to database sharding.', 'user1', 'introduction-to-database-sharding', 'Introduction to Database Sharding', TRUE, NOW(), NOW(), NOW()),
    ("blog14", 'Using JSON in MySQL', 'Learn how to store, query, and manipulate JSON data in MySQL.', 'Working with JSON in MySQL.', 'user1', 'using-json-in-mysql', 'Using JSON in MySQL', TRUE, NOW(), NOW(), NOW()),
    ("blog15", 'Mastering SQL Triggers', 'This blog explains what SQL triggers are and how to use them effectively.', 'A guide to SQL triggers.', 'user1', 'mastering-sql-triggers', 'Mastering SQL Triggers', TRUE, NOW(), NOW(), NOW()),
    ("blog16", 'Understanding ACID Properties in Databases', 'An in-depth look at the ACID properties and their importance in database management.', 'ACID properties in databases.', 'user1', 'understanding-acid-properties', 'Understanding ACID Properties in Databases', TRUE, NOW(), NOW(), NOW()),
    ("blog17", 'Best Practices for Database Design', 'This blog discusses best practices to consider when designing a database.', 'Database design best practices.', 'user1', 'best-practices-for-database-design', 'Best Practices for Database Design', TRUE, NOW(), NOW(), NOW()),
    ("blog18", 'Using Foreign Keys in SQL', 'Learn about the importance of foreign keys in SQL and how to use them.', 'A guide to using foreign keys in SQL.', 'user1', 'using-foreign-keys-in-sql', 'Using Foreign Keys in SQL', TRUE, NOW(), NOW(), NOW()),
    ("blog19", 'Database Schema Design for Beginners', 'An introductory guide to database schema design, covering key concepts and best practices.', 'Introduction to database schema design.', 'user1', 'database-schema-design-for-beginners', 'Database Schema Design for Beginners', TRUE, NOW(), NOW(), NOW()),
    ("blog20", 'Managing Database Connections Efficiently', 'Tips and techniques for managing database connections to ensure efficient performance.', 'Efficient database connection management.', 'user1', 'managing-database-connections-efficiently', 'Managing Database Connections Efficiently', TRUE, NOW(), NOW(), NOW()),
    ("blog21", 'Introduction to Data Warehousing', 'This blog provides an overview of data warehousing concepts and their applications.', 'A guide to data warehousing.', 'user1', 'introduction-to-data-warehousing', 'Introduction to Data Warehousing', TRUE, NOW(), NOW(), NOW()),
    ("blog22", 'Using SQL Views', 'Learn how to create and use SQL views for better data management.', 'A guide to SQL views.', 'user1', 'using-sql-views', 'Using SQL Views', TRUE, NOW(), NOW(), NOW()),
    ("blog23", 'Database Caching Strategies', 'An exploration of various caching strategies to improve database performance.', 'Caching strategies for databases.', 'user1', 'database-caching-strategies', 'Database Caching Strategies', TRUE, NOW(), NOW(), NOW()),
    ("blog24", 'Introduction to Graph Databases', 'An overview of graph databases and their unique advantages for certain use cases.', 'A guide to graph databases.', 'user1', 'introduction-to-graph-databases', 'Introduction to Graph Databases', TRUE, NOW(), NOW(), NOW()),
    ("blog25", 'Understanding Database Transactions', 'This blog explains what database transactions are and why they are important.', 'A guide to database transactions.', 'user1', 'understanding-database-transactions', 'Understanding Database Transactions', TRUE, NOW(), NOW(), NOW()),
    ("blog26", 'Data Modeling Techniques', 'An in-depth look at various data modeling techniques and their applications.', 'Data modeling techniques.', 'user1', 'data-modeling-techniques', 'Data Modeling Techniques', TRUE, NOW(), NOW(), NOW()),
    ("blog27", 'Using Full-Text Search in SQL', 'Learn how to implement and use full-text search capabilities in SQL databases.', 'Full-text search in SQL.', 'user1', 'using-full-text-search-in-sql', 'Using Full-Text Search in SQL', TRUE, NOW(), NOW(), NOW()),
    ("blog28", 'Implementing Data Privacy in Databases', 'Best practices for implementing data privacy measures in your database systems.', 'Data privacy in databases.', 'user1', 'implementing-data-privacy-in-databases', 'Implementing Data Privacy in Databases', TRUE, NOW(), NOW(), NOW()),
    ("blog29", 'Introduction to Time Series Databases', 'An introduction to time series databases and their unique features and benefits.', 'A guide to time series databases.', 'user1', 'introduction-to-time-series-databases', 'Introduction to Time Series Databases', TRUE, NOW(), NOW(), NOW()),
    ("blog30", 'Database Migration Tools', 'An overview of popular database migration tools and their features.', 'Database migration tools.', 'user1', 'database-migration-tools', 'Database Migration Tools', TRUE, NOW(), NOW(), NOW());


INSERT INTO tag (id, tag_name_en, tag_name_vn, created_at)
VALUES
    ('tag1', 'Technology', 'Công nghệ', NOW()),
    ('tag2', 'Science', 'Khoa học', NOW()),
    ('tag3', 'Education', 'Giáo dục', NOW()),
    ('tag4', 'Health', 'Sức khỏe', NOW()),
    ('tag5', 'Travel', 'Du lịch', NOW()),
    ('tag6', 'Food', 'Ẩm thực', NOW()),
    ('tag7', 'Lifestyle', 'Phong cách sống', NOW()),
    ('tag8', 'Finance', 'Tài chính', NOW()),
    ('tag9', 'Sports', 'Thể thao', NOW()),
    ('tag10', 'Entertainment', 'Giải trí', NOW());

INSERT INTO blog_tag (blog_id, tag_id)
VALUES
    ('blog1', 'tag1'), -- Technology
    ('blog1', 'tag2'), -- Science
    ('blog2', 'tag3'), -- Education
    ('blog2', 'tag4'), -- Health
    ('blog3', 'tag5'), -- Travel
    ('blog3', 'tag6'), -- Food
    ('blog4', 'tag7'), -- Lifestyle
    ('blog4', 'tag8'), -- Finance
    ('blog5', 'tag9'), -- Sports
    ('blog5', 'tag10'), -- Entertainment
    ('blog6', 'tag1'), -- Technology
    ('blog6', 'tag4'), -- Health
    ('blog7', 'tag5'), -- Travel
    ('blog7', 'tag2'), -- Science
    ('blog8', 'tag3'), -- Education
    ('blog8', 'tag7'), -- Lifestyle
    ('blog9', 'tag6'), -- Food
    ('blog9', 'tag8'), -- Finance
    ('blog10', 'tag9'), -- Sports
    ('blog10', 'tag10'); -- Entertainment