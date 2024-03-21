<%-- 
    Document   : productManager
    Created on : Oct 5, 2023, 5:54:48 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link href="header.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
       <div id="headerAdmin">
            <%@include file="admin.jsp" %>
        </div>
        <div>
            <ul id="edit_info">
                <li><a href="/tratieshop/ProductURL?service=listAllProduct">Show Product</a></li>
                <li><a href="addProduct.jsp">Insert Product</a></li>
            </ul>
        </div>
    </body>
</html>
