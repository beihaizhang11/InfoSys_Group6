<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>HALL OF FAME</title>
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
        input[type="text"], input[type="date"], select {
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
    <h2>HALL OF FAME</h2>
    <form action="LeaderboardServlet" method="get">
        Region: <input type="text" name="region"><br>
        Gender: <input type="text" name="gender"><br>
        Age Between: <select name="ageRange">
        <option value="">--Choose Age--</option>
        <option value="0-18">0-18</option>
        <option value="19-30">19-30</option>
        <option value="31-50">31-50</option>
        <option value="51+">51+</option>
    </select><br>
        Start Date: <input type="date" name="startDate"><br>
        End Date: <input type="date" name="endDate"><br>
        Activity Type: <input type="text" name="activityType"><br>
        <input type="submit" value="SEARCH">
    </form>
    <table>
        <tr>
            <th>User Name</th>
            <th>Total Sport Time</th>
            <th>Region</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Weight (KG)</th>
            <th>Height (CM)</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.totalDuration}</td>
                <td>${user.region}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.weight}</td>
                <td>${user.height}</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="home.jsp">BACK TO HOME</a></p>
</div>
</body>
</html>
