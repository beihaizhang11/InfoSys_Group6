<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>Edit Profile</title>
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
      padding: 20px 40px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 400px;
      text-align: center;
    }
    h2 {
      margin-bottom: 20px;
      color: #333333;
      font-size: 24px;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    label {
      text-align: left;
      margin-bottom: 5px;
      color: #666666;
    }
    input[type="text"], input[type="number"] {
      width: 100%;
      padding: 10px;
      margin: 10px 0 20px;
      border: 1px solid #cccccc;
      border-radius: 4px;
      box-sizing: border-box;
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
      transition: background-color 0.3s;
    }
    input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Edit Profile</h2>
  <form action="EditProfileServlet" method="post">
    <label for="region">Region:</label>
    <input type="text" id="region" name="region" required>
    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" required>
    <label for="age">Age:</label>
    <input type="number" id="age" name="age" required>
    <label for="weight">Weight (KG):</label>
    <input type="number" id="weight" step="0.01" name="weight" required>
    <label for="height">Height (CM):</label>
    <input type="number" id="height" step="0.01" name="height" required>
    <input type="submit" value="SUBMIT">
  </form>
</div>
</body>
</html>

