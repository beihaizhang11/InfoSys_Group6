-- 创建用户表
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- 创建运动信息表
CREATE TABLE Activities (
    activity_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    activity_type VARCHAR(255),
    date DATE,
    start_time TIME,
    duration_minutes INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
