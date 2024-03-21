<%-- 
    Document   : UserManager
    Created on : Oct 5, 2023, 2:32:46 PM
    Author     : nqtie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="i" uri="http://java.sun.com/jsp/jstl/core" %>
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
            </ul>
            </div>
            <div id="info_display_content">
                <table>
            <tr>
                <th>UserID</th>
                <th>FullName</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Gender</th>
                <th>Email</th>
                <th>UserName</th>
                <th>PassWord</th>
                <th>RoleID</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="i" items="${requestScope.data}">
                <tr>
                <td>  ${i.getUserID()}</td>
                <td>   ${i.getFullName()}</td>
                <td>   ${i.getAddress()}</td>
                <td>   ${i.getPhone()}</td>
                <td>   ${i.getGender()}</td>
                <td>   ${i.getEmail()}</td>
                <td>   ${i.getUserName()}</td>
                <td>   ${i.getPassWord()}</td>
                <td>   ${i.getRoleID()}</td>
                <td><a href="userURL?service=deleteUser&id=${i.getUserID()}&roleID=${i.getRoleID()}">Delete</td>
            </tr>
            </c:forEach>
                </table>
            </div>
        </div>
        
    </body>
</html>
