<%-- 
    Document   : ProductByCid
    Created on : Oct 18, 2023, 3:18:51 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link rel="stylesheet" href="CSS/header.css"/>
        <link rel="stylesheet" href="CSS/formsearch.css"/>
    </head>
    <body>
        <%@include file="headerHome.jsp" %>
        <div class="ProductByCid">
            <div class="URLProduct">
                <a href="home">Home</a>
                <a> / ${requestScope.categoryName}</a>
            </div>

            <div class="headerSearch">
                <form action="ProductByCidURL" method="get">
                    <input type="hidden" value="${requestScope.categoryID}" name="cid">
                    <input type="hidden" value="Search" name="service">
                    <table>
                        <tr>
                            <td>Price From <input type="number" name="PriceFrom"/></td>
                            <td>Price To <input type="number" name="PriceTo"/></td>
                            <td class="oderPrice">Price:
                                <select name="orderPrice">
                                        <option value="ASC"  > Low to High </option>
                                        <option value="DESC" > High to Low </option>
                                    </select>
                            </td>
                        </tr>
                        <tr class="button">
                            <td><input type="reset" value="CLEAR" /></td>
                            <td><input type="submit" name="submit" value="Search" /></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="productHeader">
                <h1>${requestScope.categoryName}</h1>
            </div>
            <div id="listProduct">

                <ul class="productInfo">
                    <c:forEach items="${requestScope.data}" var="p">
                        <li>
                            <a href="ProductInfoDetailURL?pid=${p.getProductId()}&cid=${requestScope.categoryID}">
                                <img src="${p.getImage()}"/>
                                <p>${p.getProductName()}</p>
                                <p class="oldPrice">Gia goc:<span class="old" >${p.getUnitPrice()}</span>VNÐ<span class="discount">${p.getDiscount()}%</span></p>
                                <p>sale: ${p.getUnitPrice()*(1-p.getDiscount()/100)}VNÐ</p>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
