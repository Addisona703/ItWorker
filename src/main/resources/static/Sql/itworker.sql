CREATE DATABASE IF NOT EXISTS itWorker;
USE itWorker;

-- 创建 articles 表
CREATE TABLE IF NOT EXISTS `articles`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `property` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) CHARSET = utf8mb4;

-- 创建 users 表
CREATE TABLE IF NOT EXISTS `users`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL UNIQUE,
    `password`   VARCHAR(255) NOT NULL,
    `image`      VARCHAR(255),
    `statement`  TEXT,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) CHARSET = utf8mb4;

-- 创建 file_uploads 表，并添加外键约束
CREATE TABLE IF NOT EXISTS `files`
(
    `id`          INT AUTO_INCREMENT PRIMARY KEY,
    `filename`    VARCHAR(255) NOT NULL,               -- 文件名
    `file_path`   VARCHAR(255) NOT NULL,               -- 文件保存路径
    `file_size`   BIGINT       NOT NULL,               -- 文件大小
    `file_type`   VARCHAR(100),                        -- 文件类型
    `upload_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 上传时间
    `user_id`     INT,                                 -- 用户ID
    `description` TEXT,                                -- 文件描述
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)      -- 关联用户表
) CHARSET = utf8mb4;

-- 插入示例数据到 articles 表
INSERT INTO `articles` (`name`, `property`)
VALUES ('Spring Boot Tutorial', 'Programming'),
       ('MySQL Basics', 'Database');

-- 插入示例数据到 users 表
INSERT INTO `users` (`name`, `password`, `image`, `statement`)
VALUES ('John Doe', 'password123', 'path/to/john_image.jpg', 'A passionate developer.');

-- 插入示例文件上传数据
INSERT INTO `files` (`filename`, `file_path`, `file_size`, `file_type`, `upload_time`, `user_id`, `description`)
VALUES
    ('spring_boot_tutorial.pdf', '/uploads/spring_boot_tutorial.pdf', 204800, 'application/pdf', CURRENT_TIMESTAMP, 1, 'Spring Boot tutorial file')