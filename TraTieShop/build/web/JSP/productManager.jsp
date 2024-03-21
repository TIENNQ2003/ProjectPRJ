<%-- 
    Document   : productManager
    Created on : Oct 5, 2023, 5:54:48 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link href="CSS/headerAdmin.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="headerAdmin">
            <%@include file="admin.jsp" %>
        </div>
        <div id="info_display">
            <div>
                <ul id="edit_info">
                    <li><a href="ProductURL?service=addProduct">Insert Product</a></li>
                </ul>
            </div>
            <div id="info_display_content">
                <h4 style="color: red">${requestScope.message}</h4>
                <div class="headerSearch">
                    <form action="ProductURL" method="get">
                        <input type="hidden" value="Search" name="service">
                        <table>
                            <tr >
                                <td>Enter ProductNameSearch:<input type="text" name="namesearch" /></td>
                                <td>Price From <input type="number" name="PriceFrom"/></td>
                                <td>Price To <input type="number" name="PriceTo"/></td>
                                <td>Price:
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
                <table>
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>CategoryID</th>
                        <th>UnitPrice</th>
                        <th>UnitinStock</th>
                        <th>Discount</th>
                        <th>Status</th>
                        <th>Image</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="i" items="${requestScope.data}">
                        <tr>
                            <td>  ${i.getProductId()}</td>
                            <td>   ${i.getProductName()}</td>
                            <td>   ${i.getCategoryID()}</td>
                            <td>   ${i.getUnitPrice()}</td>
                            <td>   ${i.getUnitinStock()}</td>
                            <td>   ${i.getDiscount()}</td>
                            <td>   ${i.getStatus()}</td>
                            <td>   <img  style="width: 60px;height: 60px;" src="${i.getImage()}" alt="alt"/></td>
                            <td><a href="ProductURL?service=updateProduct&pid=${i.getProductId()}">update</a></td>
                            <td><a href="ProductURL?service=deleteProduct&pid=${i.getProductId()}">Delete</a></td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

    </body>
</html>
