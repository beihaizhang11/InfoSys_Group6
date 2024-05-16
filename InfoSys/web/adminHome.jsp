<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>Admin Home</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      display: flex;
      flex-direction: column;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .container {
      background-color: #ffffff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 80%;
      max-width: 400px; /* Limit the width for a better vertical alignment */
      margin-top: 20px;
    }
    h2 {
      margin-bottom: 20px;
      color: #333333;
      text-align: center;
      font-size: 24px;
    }
    .actions {
      margin-top: 20px;
      display: flex;
      flex-direction: column; /* Make links vertical */
      align-items: center; /* Center align the links */
    }
    .actions a {
      margin: 10px 0; /* Add vertical space between links */
      color: #4CAF50;
      text-decoration: none;
      font-size: 16px;
      display: block;
      text-align: center;
      width: 100%; /* Ensure full width */
      padding: 10px;
      background-color: #f9f9f9;
      border-radius: 4px;
    }
    .actions a:hover {
      text-decoration: underline;
      background-color: #e9e9e9;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Admin Home</h2>
  <div class="actions">
    <a href="ViewAllUsersServlet">View All Users</a>
    <a href="ViewAllActivitiesServlet">View All Activities</a>
    <a href="setWeather.jsp">Set Weather Information</a>
    <a href="setCalories.jsp">Set Calories Burned</a>
    <a href="index.jsp">Back to Home</a> <!-- Added back to index.jsp link -->
  </div>
</div>
</body>
</html>