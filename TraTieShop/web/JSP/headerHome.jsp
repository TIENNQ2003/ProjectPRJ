<%-- 
    Document   : headerHome
    Created on : Oct 15, 2023, 9:36:06 PM
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
        <link rel="stylesheet" href="CSS/header.css"/>
    </head>
    <body>
        <%
            DecimalFormat df = new DecimalFormat("###,###");
            df.setMaximumFractionDigits(8);
        %>
        <div class="header_menu">
            <div id="header">
                <a href="home" id="header_left">
                    <img src="images/logo.png" alt="">
                    <div class="slogan">
                        <h2 class="slogan_nameshop">TratieShop</h2>
                        <p class="slogan_name">Bước đi đến thành công</p>
                    </div>
                </a>

                <div id="header_middle">
                    <form action="searchProductURL">
                        <input type="text" class="header_middle_search" name="key" placeholder="Nhập thông tin bạn muốn tìm kiếm">
                        <button type="submit"><i class="fa-solid fa-magnifying-glass "></i></button>
                    </form>
                </div>
                <div id="header_right">
                    <a href="https://www.facebook.com/tien6699"><i class="fa-brands fa-whatsapp"></i>Liên hệ</a>
                    <c:if test="${sessionScope.account!=null}"><a href="ShoppingCartURL"><i class="fa-solid fa-cart-plus"></i>Giỏ hàng</a></c:if>
                    <c:if test="${sessionScope.account==null}"><a href="loginURL"><i class="fa-solid fa-cart-plus"></i>Giỏ hàng</a></c:if>
                    <c:if test="${sessionScope.account!=null}">
                        <div id="sub_header_right">
                            <a href=""><i class="fa-solid fa-user"></i> ${sessionScope.account.getUserName()}</a>
                            <ul class="menu_login">
                                <li><a href="ProfileURL">Thông tin cá nhân</a></li>
                                <li><a href="MyPurchaseURL">Đơn mua</a></li>
                                <li><a href="logoutURL">Logout</a></li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.account==null}">
                        <a href="loginURL"><i class="fa-solid fa-user"></i>Đăng nhập</a>
                    </c:if>


                </div>
            </div>

            <div id="menu">
                <nav>
                    <ul id="main-menu">
                        <li><a href="ProductByCidURL?cid=1&cname=Nike">Nike</a></li>
                        <li><a href="ProductByCidURL?cid=2&cname=Adidas">Adidas</a></li>
                        <li><a href="ProductByCidURL?cid=3&cname=Vans">Vans</a></li>
                        <li><a href="ProductByCidURL?cid=4&cname=MLB">MLB</a></li>
                        <li><a href="ProductByCidURL?cid=5&cname=Converse">Converse</a></li>
                        <li><a href="ProductByCidURL?cid=6&cname=New Balance">New Balance</a></li>
                        <li><a href="ProductByCidURL?cid=7&cname=Phụ Kiện">Phụ Kiện</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>
