<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>View All Users</title>
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
    <h2>View All Users</h2>
    <table>
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Region</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Weight (KG)</th>
            <th>Height (CM)</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.region}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.weight}</td>
                <td>${user.height}</td>
                <td>
                    <a href="DeleteUserServlet?userId=${user.userId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="adminHome.jsp">BACK TO ADMIN HOME</a></p>
</div>
</body>
</html>
