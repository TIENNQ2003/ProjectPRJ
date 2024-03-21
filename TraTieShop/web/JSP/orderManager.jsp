<%-- 
    Document   : orderManager
    Created on : Oct 5, 2023, 2:33:37 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Vector,entity.Order,model.DAOOrder" %>
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
                    <li>
                    </li>
                </ul>
            </div>
            <div id="info_display_content">
                <h4 style="color: red">${requestScope.message}</h4>
                <div class="headerSearch">
                    <form action="OrderURL" method="get">
                        <input type="hidden" value="Search" name="service">
                        <table>
                            <tr >
                                <td>OrderDate From <input type="date" name="DateFrom"/></td>
                                <td>OrderDate To <input type="date" name="DateTo"/></td>
                                <td>Status
                                    <select name="status">
                                        <option value=""></option>
                                        <option value="0">Đang chờ xử lý</option>
                                        <option value="1">Đang giao hàng</option>
                                        <option value="2">Giao hàng thành công</option>
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
                <table class="statisticOrder">
                    <tr>
                        <td class="statisticOrderDetail">
                            <a href="OrderURL?service=Search&submit=1">
                                <div>
                                    <div>Tất cả đơn hàng</div>
                                    <div><%=request.getAttribute("TotalOrder")%></div>
                                </div>
                            </a>
                        </td>
                        <td class="statisticOrderDetail">
                            <a href="OrderURL?service=Search&status=2&submit=1">
                                <div>
                                    <div>Đơn hàng đã giao</div>
                                    <div><%=request.getAttribute("OrderSucess")%></div>
                                </div>
                            </a>
                        </td>
                        <td class="statisticOrderDetail">
                            <a href="OrderURL?service=Search&status=1&submit=1">
                                <div>
                                    <div>Đơn hàng Ðang giao</div>
                                    <div><%=request.getAttribute("orderProcessing")%></div>
                                </div>
                            </a>
                        </td>
                        <td class="statisticOrderDetail">
                            <a href="OrderURL?service=Search&status=0&submit=1">
                                <div>
                                    <div>Đơn hàng đang chờ xử lý</div>
                                    <div><%=request.getAttribute("OrderPending")%></div>
                                </div>
                            </a>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <th>OrderID</th>
                        <th>UserID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>StatusID</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>TotalPrice</th>
                        <th>UpdateStatus</th>
                        <th>View Detail</th>
                    </tr>
                    <%
                     DAOOrder dao = new DAOOrder();
                    Vector<Order> vector=(Vector<Order>)request.getAttribute("data");
                    for (Order order : vector) {
                
                    %>
                    <tr>
                        <td> <%=order.getOrderID()%></td>
                        <td>   <%=order.getUserID()%></td>
                        <td>   <%=order.getOrderDate()%></td>
                        <td>   <%=order.getRequireDate()%></td>
                        <td>   <%=order.getShippedDate()%></td>
                        <td>   <%=dao.getStatusByID(order.getStatusID()).getStatusName()%></td>
                        <td>   <%=order.getPhone()%></td>
                        <td>   <%=order.getDeliverAddress()%></td>
                        <td>   <%=df.format(order.getTotalPrice())%></td>
                        <td><a style="width: 50%;" href="OrderURL?service=updateOrderFromAdmin&statusID=1&oid=<%=order.getOrderID()%>"><button>Đang Giao hàng</button></a><a href="OrderURL?service=updateOrderFromAdmin&statusID=2&oid=<%=order.getOrderID()%>"><button>Giao hàng thành công</button></a></td>
                        <td><a href="OrderDetailUserURL?orderID=<%=order.getOrderID()%>"><button>View Detail</button></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>

    </body>
</html>
