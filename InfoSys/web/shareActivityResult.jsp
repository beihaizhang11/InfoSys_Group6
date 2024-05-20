<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Share Activity Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333333;
            font-size: 28px;
        }
        .info {
            margin: 20px 0;
            font-size: 18px;
        }
        .info p {
            margin: 10px 0;
            color: #666666;
        }
        .highlight {
            color: #4CAF50;
            font-weight: bold;
        }
        a {
            color: #ffffff;
            text-decoration: none;
            font-size: 16px;
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            background-color: #4CAF50;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Activity Share</h2>
    <div class="info">
        <p>Activity Type: <span class="highlight">${activityType}</span></p>
        <p>Duration: <span class="highlight">${duration} minutes</span></p>
        <p>Date: <span class="highlight">${date}</span></p>
        <p>Start Time: <span class="highlight">${startTime}</span></p>
        <p>Temperature: <span class="highlight">${temperature} Celsius</span></p>
        <p>Weather: <span class="highlight">${weatherCondition}</span></p>
        <p>Total Calories Burned: <span class="highlight">${totalCaloriesBurned} kcal</span></p>
    </div>
    <a href="home.jsp">Back to Home</a>
</div>
</body>
</html>
