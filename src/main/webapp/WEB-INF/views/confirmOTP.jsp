<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <title>OTP Verification Form</title>
    <link rel="stylesheet" href="style.css" />
    <!-- Boxicons CSS -->
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
   <script src="script.js" defer></script>
  </head>
  <body>
    <c:if test="${not empty message}">
        <div class="${message eq 'Error' ? 'error-message' : 'success-message'}">
            ${message}
        </div>
    </c:if>
    <a href="${pageContext.request.contextPath}/user/signin">Back to Login</a>
    <br>
    <a href="${pageContext.request.contextPath}/user/register">Back to Register</a>
  </body>
</html>