<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <style>
        /* Styles giữ nguyên như đã có */
        /* Thêm một phần nhỏ cho hiển thị thông báo */
        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .success-message {
            color: green;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title">Register</div>

    <!-- Hiển thị thông báo lỗi hoặc thành công -->
    <c:if test="${not empty message}">
        <div class="${message eq 'Error' ? 'error-message' : 'success-message'}">
            ${message}
        </div>
    </c:if>

    <!-- Form để thêm user -->
    <form action="/register" method="post">
        <div class="input-box">
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>
        <div class="input-box">
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>
        <div class="input-box">
            <label for="dateOfBirth">Date of Birth</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required>
        </div>
        <div class="input-box">
            <label for="gender">Gender</label>
            <select id="gender" name="gender" required>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
        </div>
        <div class="input-box">
            <label for="phone">Phone</label>
            <input type="text" id="phone" name="phone" required>
        </div>
        <div class="input-box">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="input-box">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="input-box">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="input-box">
            <label for="repassword">Repeat Password</label>
            <input type="password" id="repassword" name="repassword" required>
        </div>
        
        <div class="button">
            <button type="submit">Add User</button>
        </div>
    </form>
</div>
</body>
</html>
