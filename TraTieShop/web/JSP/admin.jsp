<%-- 
    Document   : headerAdmin
    Created on : Oct 5, 2023, 2:10:50 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.DecimalFormat" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link href="CSS/headerAdmin.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <%
           DecimalFormat df = new DecimalFormat("###,###");
           df.setMaximumFractionDigits(8);
        %>
        <div class="header_menu">
            <div id="header">
                <a href="admin" id="header_left">
                    <img src="images/logo.png" alt="">
                    <div class="slogan">
                        <h2 class="slogan_nameshop">TratieShop</h2>
                        <p class="slogan_name">Bước đi đến thành công</p>
                    </div>
                </a>

                <div id="header_middle">
                    <h1>Welcome ${sessionScope.account.getUserName()} </h1>
                </div>
                <div id="header_right">
                    <c:if test="${sessionScope.account==null}">
                        <a href="loginURL" style="text-decoration: none;color: black;"><i class="fa-solid fa-user"></i>Đăng nhập</a>
                    </c:if>
                    <c:if test="${sessionScope.account!=null}">
                        <a href=""><i class="fa-solid fa-user"></i>${sessionScope.account.getUserName()}</a>
                        <ul class="menu_login">
                            <li><a href="ProfileURL">Thông tin cá nhân</a></li>
                            <li><a href="logoutURL">Longout</a></li>
                        </ul>
                    </c:if>

                </div>
            </div>
            <div id="menu">
                <nav>
                    <ul id="main-menu">
                        <li><a href="admin?action=order">Order</a></li>
                        <li><a href="admin?action=product">Product</a></li>
                        <li><a href="admin?action=category">Category</a></li>
                        <li><a href="admin?action=user">User</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>
