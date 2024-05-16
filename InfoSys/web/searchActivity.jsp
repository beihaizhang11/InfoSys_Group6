<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Search Sport Activity</title>
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
            width: 600px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333333;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="text"], input[type="date"] {
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 12px;
            border: 1px solid #dddddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            color: #4CAF50;
            text-decoration: none;
            font-size: 16px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Search Sport Activity</h2>
    <form action="SearchActivityServlet" method="get">
        Activity Type: <input type="text" name="activityType"><br>
        Date: <input type="date" name="date"><br>
        <input type="submit" value="SEARCH">
    </form>
    <table>
        <tr>
            <th>Activity ID</th>
            <th>Sport Type</th>
            <th>Date</th>
            <th>Start Time</th>
            <th>Duration (minutes)</th>
        </tr>
        <c:forEach var="activity" items="${activities}">
            <tr>
                <td>${activity.activityID}</td>
                <td>${activity.activityType}</td>
                <td>${activity.date}</td>
                <td>${activity.startTime}</td>
                <td>${activity.duration}</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="home.jsp">BACK TO HOME</a></p>
</div>
</body>
</html>
