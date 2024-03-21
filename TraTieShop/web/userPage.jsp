<%-- 
    Document   : header
    Created on : Sep 27, 2023, 8:04:28 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link rel="stylesheet" href="../CSS/header.css">
    </head>
    <body>
        <div id="header">
            <a href="userPage.jsp" id="header_left">
                <img src="../images/logo.png" alt="">
                <div class="slogan">
                    <h2 class="slogan_nameshop">TratieShop</h2>
                    <p class="slogan_name">Bước đi đến thành công</p>
                </div>
            </a>

            <div id="header_middle">
                <form action="">
                    <input type="text" class="header_middle_search" placeholder="Nhập thông tin bạn muốn tìm kiếm">
                    <button><i class="fa-solid fa-magnifying-glass "></i></button>
                </form>
            </div>
            <div id="header_right">
                <a href=""><i class="fa-brands fa-whatsapp"></i>Liên hệ</a>
                <a href=""><i class="fa-solid fa-cart-plus"></i>Giỏ hàng</a>
                <a href="login.jsp"><i class="fa-solid fa-user"></i>Đăng nhập</a>
            </div>
        </div>

        <div id="menu">
            <nav>
                <ul id="main-menu">
                    <li><a href="">Nike</a></li>
                    <li><a href="">Adidas</a></li>
                    <li><a href="">Vans</a></li>
                    <li><a href="">MLB</a></li>
                    <li><a href="">Converse</a></li>
                    <li><a href="">Puma</a></li>
                    <li><a href="">Phụ Kiện</a></li>
                </ul>
            </nav>
        </div>
    </body>
</html>
