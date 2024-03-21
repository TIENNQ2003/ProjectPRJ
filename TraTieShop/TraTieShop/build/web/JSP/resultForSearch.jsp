<%-- 
    Document   : header
    Created on : Sep 27, 2023, 8:04:28 PM
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
        <link rel="stylesheet" href="CSS/header.css"/>

    </head>
    <body>
        <%@include file="headerHome.jsp" %>
        <div class="listProductSearch">
            <div id="listProduct">
            <div class="productHeader">
                <h3>Result search For "${requestScope.key}"</h3>
            </div>
            <ul class="productInfo">
                <c:forEach items="${requestScope.data}" var="p">
                    <li>
                        <a href="ProductInfoDetailURL?pid=${p.getProductId()}">
                            <img src="${p.getImage()}"/>
                            <p>${p.getProductName()}</p>
                            <p class="oldPrice">Gia goc: <span class="old" >${p.getUnitPrice()}</span>VNÐ<span class="discount">${p.getDiscount()}%</span></p>
                            <p>sale: ${p.getUnitPrice()*(1-p.getDiscount()/100)}VNÐ</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        </div>
    </body>
</html>
