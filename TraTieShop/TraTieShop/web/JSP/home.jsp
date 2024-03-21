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
        <div class="banner">
            <a><img class="mySlides" src="images/banner/01.png" alt=""></a>
            <a><img class="mySlides" src="images/banner/02.png" alt=""></a>
            <a><img class="mySlides" src="images/banner/03.png" alt=""></a>
        </div>
        <script>
            var myIndex = 0;
            carousel();
            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1;
                }
                x[myIndex - 1].style.display = "block";
                setTimeout(carousel, 4000); // Change image every 2 seconds
            }
        </script>
        <div id="listProduct">
            <div class="productHeader">
                <h3>Sản phẩm bán chạy</h3>
            </div>
            <ul class="productInfo">
                <c:forEach items="${requestScope.data}" var="p">
                    <li>
                        <a href="ProductInfoDetailURL?pid=${p.getProductId()}&cid=${p.getCategoryID()}">
                            <img src="${p.getImage()}"/>
                            <p>${p.getProductName()}</p>
                            <p class="oldPrice">Gia goc: <span class="old" >${p.getUnitPrice()}</span>VNÐ<span class="discount">${p.getDiscount()}%</span></p>
                            <p>sale: ${p.getUnitPrice()*(1-p.getDiscount()/100)}VNÐ</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
