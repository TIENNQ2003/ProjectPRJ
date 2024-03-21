<%-- 
    Document   : register
    Created on : Sep 28, 2023, 8:25:56 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form action="registerURL" method="post">
                <input type="hidden" name="service" value="addUser" />
                <h1>Register</h1>
                <div class="input_box">
                    <input type="text" placeholder="Fullname" name="Fullname" required>
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="input_box">
                    <input type="text" placeholder="Address" name="Address" required>
                    <i class="fa-solid fa-location-dot"></i>
                </div>
                <div class="input_box">
                    <input type="text" placeholder="Phone" name="Phone" required>
                    <i class="fa-solid fa-phone"></i>
                </div>
                <div class="radio_box">
                    <p style="font-size: 16px; margin-left: 20px; margin-right: 60px;">Gender</p>
                    <input  style="font-size: 16px;margin-right:10px;" type="radio" name="Gender" value="1" required/>Male
                    <input  style="font-size: 16px;margin-right:10px;margin-left: 90px;" type="radio" name="Gender" value="0" required/>Female
                </div>
                <div class="input_box">
                    <input type="email" placeholder="Email" name="Email" required>
                    <i class="fa-solid fa-envelope"></i>
                </div>
                <div class="input_box">
                    <input type="text" placeholder="Username" name="Username" required>
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="input_box">
                    <input type="password" placeholder="Password" name="Password" required>
                    <i class="fa-solid fa-lock"></i>
                </div>
<!--                                <div class="remember-forgot">
                                    <Label><input type="checkbox">Remember password</Label>
                                </div>-->
                <h3 style="color: red; margin-bottom: 5px;margin-top: -20px;">${requestScope.message}</h3>
                <button  type="submit" name="submit" class="btn">Register</button>
                <div class="register-link">
                    <p>Don't have an account?<a href="loginURL">Login</a></p>
                </div>
            </form>
        </div>
    </body>
</html>
