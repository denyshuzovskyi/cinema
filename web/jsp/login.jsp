<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div class="login">
        <form name="login_form" method="post" action="/controller">

            <input type="hidden" name="command" value="login"/>
            <label for="email">Email</label>
            <input type="email" id="email" placeholder="Enter Email" name="email" required><br>

            <label for="password">Password</label>
            <input type="password" id="password" placeholder="Enter Password" name="password" required><br>

            ${authentication_error_message} <br>

            <button type="submit">Login</button>

        </form>

        <p>Forgot <a href="#">password?</a></p>

        <p><a href="user/registration.jsp">Register</a>  if you dont't have an account</p>

    </div>
</body>
</html>