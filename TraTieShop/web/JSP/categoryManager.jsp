<%-- 
    Document   : CategoryManager
    Created on : Oct 5, 2023, 2:13:48 PM
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
        <link rel="stylesheet" href="CSS/headerAdmin.css">
    </head>
   <body>
       <div id="headerAdmin">
            <%@include file="admin.jsp" %>
        </div>
        <div id="info_display">
            <div>
                <ul id="edit_info">
                <li><a href="CategoryURL?service=addCategory">Insert Category</a></li>
            </ul>
            </div>
            <div id="info_display_content">
                <table>
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="i" items="${requestScope.data}">
                <tr>
                <td>  ${i.getCategoryID()}</td>
                <td>   ${i.getCategoryName()}</td>
                <td><a href="CategoryURL?service=updateCategory&cid=${i.getCategoryID()}">update</a></td>
                <td><a href="CategoryURL?service=deleteCategory&cid=${i.getCategoryID()}">delete</a></td>
            </tr>
            </c:forEach>
                </table>
            </div>
        </div>
        
    </body>
</html>
