<%-- 
    Document   : headerAdmin
    Created on : Oct 5, 2023, 2:10:50 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link href="../CSS/header.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <div id="header">
            <a href="admin.jsp" id="header_left">
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
                <a href=""><i class="fa-solid fa-user"></i>Tài Khoản</a>
            </div>
        </div>

        <div id="menu">
            <nav>
                <ul id="main-menu">
                    <li><a href="/tratieshop/admin?action=category">Category</a></li>
                    <li><a href="/tratieshop/admin?action=product">Product</a></li>
                    <li><a href="/tratieshop/admin?action=user">User</a></li>
                    <li><a href="/tratieshop/admin?action=order">Order</a></li>
                </ul>
            </nav>
        </div>
    </body>
</html>
