<%-- 
    Document   : infoUser
    Created on : Oct 15, 2023, 9:32:47 PM
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
                            <c:if test="${sessionScope.account.getRoleID()==2}">
                            <li><a  href="MyPurchaseURL">My Purchase </a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
            <div id="info_user_info_detail">
                <h4 style="color: red">${requestScope.message}</h4>
                <div class="form_header">
                    <h2>My Profile</h2>
                    <h4>Manage and protect your account</h4>
                </div>
                <div class="form">
                    <form class="form_info" action="ProfileURL?service=updateProfile&id=${sessionScope.account.userID}" method="post" style="border: 2px;">
                        <input type="hidden" name="PassWord" value="${sessionScope.account.getPassWord()}" />
                        <input type="hidden" name="UserName" value="${sessionScope.account.getUserName()}" />
                        <input type="hidden" name="RoleID" value="${sessionScope.account.getRoleID()}" />
                        <table class="form_layout">
                            <tr class="form_detail">
                                <td class="input_title">UserName:</td>
                                <td class="input_info">${sessionScope.account.userName}</br></td>
                            </tr>
                            <tr class="form_detail">
                                <td class="input_title">Full Name:</td>
                                <td class="input_info"><input type="text" value="${sessionScope.account.getFullName()}" name="FullName" /></br></td>
                            </tr>
                            <tr class="form_detail">
                                <td class="input_title">Address:</td>
                                <td class="input_info"><input type="text" value="${sessionScope.account.getAddress()}" name="Address" /></br></td>
                            </tr>
                            <tr class="form_detail">
                                <td class="input_title">Phone:</td>
                                <td class="input_info"><input type="text" value="${sessionScope.account.getPhone()}" name="Phone" /></br></td>
                            </tr>
                            <tr class="form_detail">
                                <td class="input_title">Gender:</td>
                                <td class="input_info_radio">
                                    <input type="radio" name="Gender" value="1" ${sessionScope.account.getGender()==1?"checked":""}  />Male
                                    <input type="radio" name="Gender" value="0" ${sessionScope.account.getGender()==0?"checked":""} />Female
                                    </br>
                                </td>
                            </tr>
                            <tr class="form_detail">
                                <td class="input_title">Email:</td>
                                <td class="input_info"><input type="text" value="${sessionScope.account.getEmail()}" name="Email" /></br></td>
                            </tr>
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
