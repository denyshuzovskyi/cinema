<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div class="registration">
        <form name="registration_form" method="post" action="/controller">
            <input type="hidden" name="command" value="register"/>
            <label for="surname">Surname</label>
            <input type="text" id="surname" placeholder="Enter Surname" name="surname" required><br>

            <label for="name">Name</label>
            <input type="text" id="name" placeholder="Enter Name" name="name" required><br>

            <label for="birthday">Birthday</label>
            <input type="date" id="birthday" placeholder="Enter Birthday" name="birthday" required><br>

            <label for="email">Email</label>
            <input type="email" id="email" placeholder="Enter Email" name="email" required><br>

            <label for="password">Password</label>
            <input type="password" id="password" placeholder="Enter Password" name="password" required><br>

            <label for="repeated_password">Repeat Password</label>
            <input type="password" id="repeated_password" placeholder="Repeat Password" name="repeated_password" required><br>

            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
