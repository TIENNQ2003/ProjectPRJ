<%-- 
    Document   : CategoryManager
    Created on : Oct 5, 2023, 2:13:48 PM
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
       <div id="headerAdmin">
            <%@include file="admin.jsp" %>
        </div>
         <div>
        <ul id="edit_info">
            <li><a href="">Show Category</a></li>
            <li><a href="">Insert Category</a></li>
        </ul>
    </div>
    </body>
</html>
