<%-- 
    Document   : orderManager
    Created on : Oct 5, 2023, 2:33:37 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.OrderDetail,entity.Product,java.util.Vector,entity.Product,model.DAOProduct,model.DAOOrder,entity.Order" %>

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
              <%
                        DAOOrder daoorder = new DAOOrder();
                        Order order=(Order)request.getAttribute("order");
              %>
            <div id="info_displayDetail">
                <h2 style="text-align: center;">Order Detail</h2>
                <table class="info_displayDetailinFoUser">
                    <tr>
                        <td><div>ReiceiverName:</div></td>
                        <td><%=order.getReceiverName()%></td>
                    </tr>
                    <tr>
                        <td><div>OrderDate:</div></td>
                        <td><%=order.getOrderDate()%></td>
                    </tr>
                    <tr>
                        <td><div>Status:</div></td>
                        <td><%=daoorder.getStatusByID(order.getStatusID()).getStatusName()%></td>
                    </tr>
                    <tr>
                        <td><div>Phone:</div></td>
                        <td><%=order.getPhone()%></td>
                    </tr>
                    <tr>
                        <td><div>Address:</div></td>
                        <td><%=order.getDeliverAddress()%></td>
                    </tr>
                </table>
                <table class="ProInOrderDetail">
                    <tr>
                        <th>So TT</th>
                        <th>ProductImage</th>
                        <th>ProductName</th>
                        <th>SubPrice</th>
                        <th>Quantity</th>
                        <th>SubTOtal</th>
                    </tr>
                    <%
                         DAOProduct dao = new DAOProduct();
                        int sum=0;
                        int i=1;
                     Vector<OrderDetail> vector=(Vector<OrderDetail>)request.getAttribute("data");
                       for (OrderDetail orderDetail : vector) {
                       Product pro =dao.getProductByID(orderDetail.getProductID());
                       sum+=pro.getUnitPrice()*(1-pro.getDiscount()/100)*orderDetail.getQuantity();
                    %>
                    <tr class="ProInfoInOrderDetail" style="margin-top: 20px;">
                        <td>
                            <div><%=i++%></div>
                        </td>
                        <td>
                            <img src="<%=pro.getImage()%>" width="70px" height="70px" />
                        </td>
                        <td>
                            <div style="width: 200px"><%=pro.getProductName()%></div>
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
                        <td></td>
                        <td><div style="text-align: left; font-weight: 600;">ToTal:</div></td>
                        <td><div style="text-align: left;"><%=df.format(sum)%> VNÐ</div></td>
                    </tr>
                </table>
            </div>
        </div>

    </body>
</html>
