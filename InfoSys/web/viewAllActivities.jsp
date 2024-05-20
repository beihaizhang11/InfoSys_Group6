<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>View All Activities</title>
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
            max-width: 1200px;
            margin-top: 20px;
        }
        h2 {
            margin-bottom: 20px;
            color: #333333;
            text-align: center;
            font-size: 24px;
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
            margin-top: 20px;
            display: inline-block;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>View All Activities</h2>
    <table>
        <tr>
            <th>Activity ID</th>
            <th>User ID</th>
            <th>Activity Type</th>
            <th>Date</th>
            <th>Start Time</th>
            <th>Duration (minutes)</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="activity" items="${activities}">
            <tr>
                <td>${activity.activityId}</td>
                <td>${activity.userId}</td>
                <td>${activity.activityType}</td>
                <td>${activity.date}</td>
                <td>${activity.startTime}</td>
                <td>${activity.durationMinutes}</td>
                <td>
                    <a href="DeleteActivity4AdminServlet?activityId=${activity.activityId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="adminHome.jsp">BACK TO ADMIN HOME</a></p>
</div>
</body>
</html>

