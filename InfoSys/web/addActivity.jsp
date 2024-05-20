<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Add Sport Activity</title>
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
        form {
            display: flex;
            flex-direction: column;
        }
        input[type="text"], input[type="date"], input[type="time"], input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #cccccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add Sport Activity</h2>
    <form action="AddActivityServlet" method="post">
        Sport Type: <input type="text" name="activityType" required><br>
        Date: <input type="date" name="date" required><br>
        Start time: <input type="time" name="startTime" required><br>
        Continued for: <input type="number" name="duration" required><br>
        <input type="submit" value="ADD">
    </form>
</div>
</body>
</html>