<%-- 
    Document   : infoUser
    Created on : Oct 15, 2023, 9:32:47 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Order,java.util.Vector,model.DAOOrder" %>
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
            DAOOrder dao=new DAOOrder();
            
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
                            <c:if test="${sessionScope.account.getRoleID()==2}">
                            <li><a  href="MyPurchaseURL">My Purchase </a></li>
                            </c:if>

                    </ul>
                </div>
            </div>
            <div id="info_user_info_detail">
                <div class="form_header">
                    <h2>My order</h2>
                    <h4>Manage Your Order</h4>
                </div>
                <%
                 Vector<Order> vector=(Vector<Order>)request.getAttribute("data");
                 for (Order order : vector) {
                %>

                <div class="orderInfo">
                    <table class="orderInfoDetail">
                        <tr class="orderInfoDetail_info">
                            <td>Order ID:</div>
                            <td><%=order.getOrderID()%></div>
                        </tr> 
                        <tr class="orderInfoDetail_info">
                            <td>ReceiverName</div>
                            <td><%=order.getReceiverName()%></div>
                        </tr>
                        <tr class="orderInfoDetail_info">
                            <td>OrderDAte:</div>
                            <td><%=order.getOrderDate()%></div>
                        </tr>
                        <tr class="orderInfoDetail_info">
                            <td>Status:</div>
                            <td><%=dao.getStatusByID(order.getStatusID()).getStatusName()%></div>
                        </tr>
                        <tr class="orderInfoDetail_info">
                            <td>Phone:</div>
                            <td><%=order.getPhone()%></div> 
                        </tr>
                        <tr class="orderInfoDetail_info">
                            <td>Address:</div>
                            <td><%=order.getDeliverAddress()%></div> 
                        </tr>
                        <tr class="orderInfoDetail_info">
                            <td>Total Price:</div>
                            <td><%=df.format(order.getTotalPrice())%> VNÐ</div> 
                        </tr>
                        <tr class="orderInfoSumit">
                            <td><a href="OrderDetailUserURL?orderID=<%=order.getOrderID()%>">Xem chi tiết đơn hàng</a></td>
                            <%
                                if(order.getStatusID()==1 || order.getStatusID()==2){
                            %>
                            <td><a href="OrderURL?service=updateOrderFromUser&statusID=2&oid=<%=order.getOrderID()%>">Đã nhận được hàng</a></td>
                            <%
                            }else{
                            %>
                            <td ><a style="background-color: #eee;">Đã nhận được hàng</a></td>
                            <%}%>
                        </tr>
                    </table>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
