<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>HOME</title>
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
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            color: #4CAF50;
            text-decoration: none;
            font-size: 16px;
        }
        a:hover {
            text-decoration: underline;
        }
        p {
            margin-top: 20px;
        }
        .logout {
            display: block;
            margin-top: 20px;
            padding: 10px;
            background-color: #f44336;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .logout:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome, ${username}</h2>
    <ul>
        <li><a href="addActivity.jsp">Add Sport Activity</a></li>
        <li><a href="deleteActivity.jsp">Delete Sport Activity</a></li>
        <li><a href="searchActivity.jsp">Search Sport Activity</a></li>
        <li><a href="totalDuration.jsp">Compute Total Sport time</a></li>
        <li><a href="leaderboard.jsp">Rankings</a></li>
        <li><a href="setProfile.jsp">Set profile</a></li>
        <li><a href="editProfile.jsp">Edit profile</a></li>
        <li><a href="editAccount.jsp">Edit Account</a></li>
        <li><a href="shareActivity.jsp">Share Activity</a></li>
    </ul>
    <a href="LogoutServlet" class="logout">LOGOUT</a>
</div>
</body>
</html>


