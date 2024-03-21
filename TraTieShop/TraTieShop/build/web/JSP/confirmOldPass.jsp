<%-- 
    Document   : confirmOldPass
    Created on : Oct 15, 2023, 11:02:24 PM
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
        <link rel="stylesheet" href="CSS/changpass.css"/>
    </head>
    <body>
        <c:if test="${sessionScope.account.getRoleID()==2}">
            <%@include file="headerHome.jsp" %>
        </c:if>
        <c:if test="${sessionScope.account.getRoleID()==1}">
            <%@include file="admin.jsp" %>
        </c:if>
        <div class="form">
            <form action="confirmOldPass" method="post">
                <h3>Enter Your old password</h3>
                <input type="password" name="oldPass" placeholder="Input the password to verify"></br>
                <input type="hidden" name="userName" value="${sessionScope.account.getUserName()}" />
                <p style="color: red;padding: 0px auto;">${requestScope.error}</p>
                <input type="submit" value="Confirm" />
            </form> 
        </div>
    </body>
</html>
