<%-- 
    Document   : infoUser
    Created on : Oct 15, 2023, 9:32:47 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.OrderDetail,entity.Product,java.util.Vector,entity.Product,model.DAOProduct" %>
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
        <%
                           DAOProduct dao = new DAOProduct();
        %>
        <%@include file="headerHome.jsp" %>
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
                        <li><a  href="MyPurchaseURL">My Purchase </a></li>
                    </ul>
                </div>
            </div>
            <div id="info_user_info_detail">
                <div class="form_header">
                    <h2>My order details</h2>
                    <h4>Manage Your Order Details</h4>
                </div>
                    <table class="ProInOrderDetail">
                        <tr>
                        <th>Image</th>
                        <th>ProductName</th>
                        <th>SubPrice</th>
                        <th>Quantity</th>
                        <th>SubTOtal</th>
                        </tr>
                        <%
                            int sum=0;
                         Vector<OrderDetail> vector=(Vector<OrderDetail>)request.getAttribute("data");
                           for (OrderDetail orderDetail : vector) {
                           Product pro =dao.getProductByID(orderDetail.getProductID());
                           sum+=pro.getUnitPrice()*(1-pro.getDiscount()/100)*orderDetail.getQuantity();
                        %>
                        <tr class="ProInfoInOrderDetail">
                            <td>
                                <img class="proImage" style="width: 70px;display: inline" src="<%=pro.getImage()%>">
                            </td>
                            <td>
                                <div style="width: 200px;margin-right: 20px;"><%=pro.getProductName()%></div>
                            </td>
                            <td>
                                <div><%=df.format(pro.getUnitPrice()*(1-pro.getDiscount()/100))%></div>
                            </td>
                            <td>
                                <div>x<%=orderDetail.getQuantity()%></div>
                            </td>
                            <td>
                                <div><%=df.format(pro.getUnitPrice()*(1-pro.getDiscount()/100)*orderDetail.getQuantity())%> VNÐ </div>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><div style="text-align: left; font-weight: 600;">ToTal:</div></td>
                            <td><div style="text-align: left;"><%=df.format(sum)%> VNÐ</div></td>
                        </tr>
                    </table>
            </div>
        </div>
    </body>
</html>
