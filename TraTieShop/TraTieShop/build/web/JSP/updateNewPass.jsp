<%-- 
    Document   : updateNewPass
    Created on : Oct 15, 2023, 11:02:52 PM
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
        <link href="CSS/edit_info.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <c:if test="${sessionScope.account.getRoleID()==2}">
            <%@include file="headerHome.jsp" %>
        </c:if>
        <c:if test="${sessionScope.account.getRoleID()==1}">
            <%@include file="admin.jsp" %>
        </c:if>
        <div id="info_user">
            <div id="info_user_manager">
                <div class="info_user_manager_icon">
                    <i class="fa-solid fa-circle-user"></i>
                    <div class="info_user_manager_info">
                        <h2>${sessionScope.account.userName}</h2>
                        <h4>Edit profile</h4>
                    </div>
                </div>
                <div class="info_user_manager_detail">
                    <ul>
                        <li><a  href="ProfileURL">My Profile</a></li>
                        <li><a  href="confirmOldPass">Change password</a></li>
                        <li><a  href="ProfileURL">My Purchase ${sessionScope.userchangepass}</a></li>
                    </ul>
                </div>
            </div>
            <div id="info_user_info_detail">
                <div class="form_header">

                    <h2>Change Password</h2>
                    <h4>For your account's security, do not share your password with anyone else</h4>
                </div>
                <div class="form">
                    <form class="form_info" action="updatepassword?id=${sessionScope.account.userID}" method="post" style="border: 2px;">
                        <table class="form_layout">
                            <tr class="form_detail">
                                <td class="input_title">New password:</td>
                                <td class="input_info"><input type="text"" name="newpass" /></br></td>
                            </tr>
                            <tr class="form_detail">
                                <td class="input_title">Confirm new password:</td>
                                <td class="input_info"><input type="text"" name="confirmnewpass" /></br></td>
                            </tr>
                            <h4 style="color: red">${requestScope.message}</h4>
                            <tr class="button">
                                <td></td>
                                <td><input type="submit" value="Save" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
