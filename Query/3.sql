-- Create table for admin accounts (if necessary)
CREATE TABLE Admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Create table for daily weather information
CREATE TABLE Weather (
    date DATE PRIMARY KEY,
    weekday VARCHAR(20),
    temperature FLOAT,
    weather_condition VARCHAR(50)
);

-- Create table for calories burned per activity
CREATE TABLE ActivityCalories (
    activity_type VARCHAR(50) PRIMARY KEY,
    calories_per_minute FLOAT
);
