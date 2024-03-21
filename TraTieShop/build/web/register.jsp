<%-- 
    Document   : register
    Created on : Sep 28, 2023, 8:25:56 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link rel="stylesheet" href="../CSS/loginAndRegister.css">
<!--        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .wrapper {
                width: 420px;
                background-color: #E4DCD1;
                color: black;
                border-radius: 10px;
                padding: 30px 40px;
            }

            .wrapper h1 {
                font-size: 36px;
                text-align: center;
            }

            .wrapper .input_box {
                position: relative;
                width: 100%;
                height: 50px;
                margin: 30px 0;
            }

            .input_box input {
                width: 100%;
                height: 100%;
                border: none;
                outline: none;
                border: 2px solid white;
                border-radius: 40px;
                font-size: 16px;
                padding: 20px 45px 20px 20px;
            }

            .input_box input::placeholder {
                color: black;
            }

            .input_box i {
                position: absolute;
                right: 20px;
                top: 50%;
                transform: translateY(-50%);
                font-size: 20px;
            }

            .wrapper .remember-forgot {
                display: flex;
                justify-content: space-between;
                font-size: 14.5px;
                margin: -15px 0 15px;
            }
            .remember-forgot label input{
                accent-color: white;
                margin-right: 3px;
            }
            .remember-forgot a{
                color: black;
                text-decoration: none;
            }
            .remember-forgot a:hover{
                text-decoration: underline;
            }
            .wrapper .btn{
                width: 100%;
                height: 45px;
                background-color: white;
                border: none;
                outline: none;
                border-radius: 40px;
                box-shadow: 0 0 10px black;
                cursor: pointer;
                font-size: 16px;
                font-weight: 600;
            }
            .wrapper .register-link{
                font-size: 14.5px;
                text-align: center;
                margin: 20px 0 15px;
            }
            .register-link p a{
                text-decoration: none;
                color: black;
                font-weight: 600;
                margin-left: 2px;
            }
            .register-link p a:hover{
                text-decoration: underline;
            }
        </style>-->
    </head>
    <body>
        <div class="wrapper">
            <form action="">
                <h1>Register</h1>
                <div class="input_box">
                    <input type="text" placeholder="Username" required>
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="input_box">
                    <input type="password" placeholder="Password" required>
                    <i class="fa-solid fa-lock"></i>
                </div>
                <div class="input_box">
                    <input type="email" placeholder="Email" required>
                    <i class="fa-solid fa-envelope"></i>
                </div>
                <div class="input_box">
                    <input type="text" placeholder="Phone" required>
                    <i class="fa-solid fa-phone"></i>
                </div>
                <div class="input_box">
                    <input type="text" placeholder="Address" required>
                    <i class="fa-solid fa-location-dot"></i>
                </div>

                <div class="remember-forgot">
                    <Label><input type="checkbox">Remember password</Label>
                </div>
                <button type="submit" class="btn">Register</button>
                <div class="register-link">
                    <p>Don't have an account?<a href="login.jsp">Login</a></p>
                </div>
            </form>
        </div>
    </body>
</html>
