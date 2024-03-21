<%-- 
    Document   : placeOrders
    Created on : Oct 27, 2023, 9:34:46 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/placeOrder.css"/>
    </head>
    <body>
        <%
            double oldTotalPrice=(Double) request.getAttribute("oldTotalPrice");
            double Discount=(Double)request.getAttribute("Discount");
            double totalPriceAll=(Double)request.getAttribute("totalPriceAll");
        %>
        <%@include file="headerHome.jsp" %>
        <div class="placeOrder">
            <div class="URLPlaceOrder">
                <a href="home">Home</a>
                <a href=""> / Giỏ hàng</a>
                <a> / Đặt hàng</a>
            </div>
            <div class="PlaceOrderTitle">
                <h4><b style="font-size: 30px;">Thông tin đơn hàng</b></h4>
            </div>
            <div class="placeorderInfo">
                <form action="OrderURL" class="placeorderInfoForm">
                    <input type="hidden" name="service" value="addOrder"/>
                    <input type="hidden" name="check" value="${requestScope.check}"/>
                    <input type="hidden" name="proID" value="${requestScope.proID}"/>
                    <input type="hidden" name="quantity" value="${requestScope.quantity}"/>
                    <div class="receiverInfo">
                        <h4>Thông tin giao hàng </h4>
                        <hr>
                        <div class="receiverInfo_detail">
                            Họ và tên người nhận hàng</br>
                            <input name="name" type="text" value="${sessionScope.account.getFullName()}"/>
                        </div>
                        <div class="receiverInfo_detail">
                            Số điện thoại người nhận hàng</br>
                            <input name="phone" type="text" value="${sessionScope.account.getPhone()}"/>
                        </div >
                        <div class="receiverInfo_detail">
                            Ðịa chỉ nhận hàng</br>
                            <input name="address" type="text" value="${sessionScope.account.getAddress()}"/>
                        </div>
                    </div>
                    <div class="oderInfo">
                        <h4>Thông tin đơn hàng</h4>
                        <hr>
                        <div class="oldTotalPrice">
                            <div  style="padding-left:0;">Price</div>
                            <div name="oldTotalPrice" ><%=df.format(oldTotalPrice)%> VNÐ</div>
                        </div>
                        <div class="Discount">
                            <div  style="padding-left:0;">Discount</div>
                            <div name="discount" ><%=df.format(Discount)%> VNÐ</div>
                        </div>
                        <div class="totalPrice" style="">
                            <div  class="">TOTAL PRICE</div>
                            <div name="totalPriceAll" ><%=df.format(totalPriceAll)%> VNÐ</div>
                            <input type="hidden" name="totalAmount" value="${requestScope.totalPriceAll}"/>
                        </div>
                        <div class="submit"><input type="submit" name="submit" value="Place Order"/></div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
