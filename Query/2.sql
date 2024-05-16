USE SportActivityDB;

CREATE TABLE UserProfile (
    user_id INT PRIMARY KEY,
    region VARCHAR(255),
    gender VARCHAR(10),
    age INT,
    weight DECIMAL(5, 2),
    height DECIMAL(5, 2),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
