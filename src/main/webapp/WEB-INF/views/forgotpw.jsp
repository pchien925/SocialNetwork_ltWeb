<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: black;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.email {
	margin-bottom: 25px;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
	background-color: #f1f1f1;
	text-align: center;
}
</style>
</head>
<body>

	<div>
		<h1>Forgot Your Password?</h1>
		<form action="user/forgotpw" method="post">
			<c:if test="${not empty message}">
				<div
					class="${message eq 'Error' ? 'error-message' : 'success-message'}">
					${message}</div>
			</c:if>
			<div class="content-form-page">
				<div class="row">
					<div class="col-md-7 col-sm-7">


						<label for="email" class="col-lg-4 control-label">Email</label>
						<div class="col-lg-8">
							<input type="text" name="email" class="form-control" id="email"
								value="${email}">
						</div>
						<div class="form-group">
							<c:if test="${alert !=null}">
								<h3 class="alert alert danger">${alert}</h3>
							</c:if>
							<label for="email" class="col-lg-4 control-label">Password</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="email"
									name="password">
							</div>
							<label for="email" class="col-lg-4 control-label">Repeat
								Password</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="email"
									name="repassword">
							</div>
							<label for="email" class="col-lg-4 control-label">OTP</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="email" name="otp">
							</div>
						</div>
						<input type="text" class="form-control" id="email" name="username"
							value="1" hidden="false">
						<div class="row">
							<div
								class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-5">
								<button type="submit" class="btn btn-primary">Send</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>