<%-- 
    Document   : login
    Created on : Sep 28, 2023, 8:24:05 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link rel="stylesheet" href="CSS/loginAndRegister.css">
    </head>
    <body>
        <div class="wrapper">
            <form action="loginURL" method="post">
                <h1>Login</h1>
                <div class="input_box">
                    <input type="text" placeholder="Username" name="Username" required>
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="input_box">
                    <input type="password" placeholder="Password" name="Password" required>
                    <i class="fa-solid fa-lock"></i>
                </div>
<!--                <div class="remember-forgot">
                    <Label><input type="checkbox">Remember password</Label>
                    <a href="">Forgot password?</a>
                </div>-->
                <h4 style="color: red">${requestScope.error}</h4>
                <button type="submit" class="btn">Login</button>
                <div class="register-link">
                    <p>Don't have an account?<a href="registerURL">Register</a></p>
                </div>
            </form>
        </div>
    </body>
</html>
