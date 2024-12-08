<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <style>
        /* Importing Google Fonts - Poppins */
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        .container {
    max-width: 500px;
    width: 100%;
    max-height: 90vh; /* Giới hạn chiều cao */
    overflow-y: auto; /* Hiển thị thanh cuộn dọc khi cần */
    background-color: #fff;
    padding: 25px 30px;
    border-radius: 5px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
}


        .container .title {
            font-size: 25px;
            font-weight: 500;
            position: relative;
            margin-bottom: 20px;
        }

        .container .title::before {
            content: "";
            position: absolute;
            left: 0;
            bottom: 0;
            height: 3px;
            width: 30px;
            border-radius: 5px;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        form {
            margin-bottom: 20px;
        }

        form .input-box {
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
        }

        form .input-box label {
            font-weight: 500;
            margin-bottom: 5px;
        }

        form .input-box input,
        form .input-box select {
            height: 40px;
            padding: 0 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
            transition: border-color 0.3s ease;
        }

        form .input-box input:focus,
        form .input-box select:focus {
            border-color: #9b59b6;
        }

        form .button {
            display: flex;
            justify-content: flex-end;
        }

        form .button button {
            height: 40px;
            width: 150px;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 500;
            transition: background 0.3s ease;
        }

        form .button button:hover {
            background: linear-gradient(-135deg, #71b7e6, #9b59b6);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f4f4f4;
        }

        td a {
            color: #9b59b6;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        td a:hover {
            color: #71b7e6;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title">Login</div>

    <!-- Form to add a new user -->
    <form action="/login" method="post">
        <c:if test="${not empty message}">
        <div class="${message eq 'Error' ? 'error-message' : 'success-message'}">
            ${message}
        </div>
   	 </c:if>
        <div class="input-box">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="input-box">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="button">
            <button type="submit">submit</button>
        </div>
    </form>
</div>
</body>
</html>
