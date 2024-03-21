<%-- 
    Document   : ShoppingCart
    Created on : Oct 23, 2023, 11:16:53 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Cart,entity.Product,model.DAOProduct" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/cart.css">
    </head>
    <body>
        <%@include file="headerHome.jsp" %>
        <div class="shoppingCart">
            <div class="URLShoppingCart">
                <a href="home">Home</a>
                <a> / Shopping Cart</a>
            </div>
            <div class="Cart_info">
                <div class="Product ">
                    <div class="title">
                        <div ><h4><b style="font-size: 30px;">Shopping Cart</b></h4></div>
                        <div style="color: grey; margin-right: -70px;" >${requestScope.numberPro-1} items</div>
                    </div>
                    <h4 style="color: red">${requestScope.error}</h4>
                    <table class="cart_productInfo_table">
                        <%
                            int oldTotalPrice=0,totalPrice=0;
                     java.util.Enumeration em = session.getAttributeNames();
                     while (em.hasMoreElements()) {
                         String id = em.nextElement().toString(); //get key
                     if (!id.equals("account")) {
                         Cart cart = (Cart) session.getAttribute(id); //get value
                          int proid=Integer.parseInt(id);
                         DAOProduct dao = new DAOProduct();
                         Product pro =dao.getProductByID(proid);
                         oldTotalPrice+=pro.getUnitPrice()*cart.getQuantity();
                         totalPrice+=pro.getUnitPrice()*(1-pro.getDiscount()/100)*cart.getQuantity();
                        %>
                        <tr class="cart_productInfo">
                            <td><img class="proImage" src="<%=pro.getImage()%>"></td>
                            <td> 
                                <div class="ProName"><%=pro.getProductName()%></div>
                            </td>
                            <td><div style="color: grey;text-decoration: line-through;font-size: 12px;"><%=df.format(pro.getUnitPrice())%> VNÐ</div></td>
                            <td><div name="subPrice" value="<%=pro.getUnitPrice()*(1-pro.getDiscount()/100)%>"><%=df.format(pro.getUnitPrice()*(1-pro.getDiscount()/100))%> VNÐ</div></td>
                        <form></form>
                        <td> 
                            <input style="width: 40px;height: 20px;font-size: 12px;margin-top: 20px;" type="number" min="1" max="<%=pro.getUnitinStock()%>" name="quantityValue" value="<%=cart.getQuantity()%>">
                        </td>

                        <td><div  name="Price" style="font-size: 15px;" value="<%=df.format(pro.getUnitPrice()*(1-pro.getDiscount()/100)*cart.getQuantity())%>"><%=df.format(pro.getUnitPrice()*(1-pro.getDiscount()/100)*cart.getQuantity())%> VNÐ </div></td>
                        <td>
                            <form action="CartURL">
                                <input type="hidden" value="updateCart" name="service"/>
                                <input type="hidden" value="<%=proid%>" name="pid"/>
                                <input type="hidden" value="<%=cart.getQuantity()%>" name="quantityUpdate"/>
                                <input type="submit" style="font-size: 12px;margin-top: 20px;" value="update" />
                            </form>

                        </td>
                        <td><div><a href="CartURL?service=deleteCart&pid=<%=id%>"><span class="close">&#10005;</span></a></div></td>
                        </tr>
                        <%
                            }
                            }
                        %>
                    </table>
                    <div class="back-to-shop"><a href="home">&leftarrow; <span class="text-muted">Back to shop</span></a></div>
                </div>
                <div class="summary">
                    <div><h5><b style="font-size: 25px;">Summary</b></h5></div>
                    <hr>

                    <div class="oldTotalPrice">
                        <div  style="padding-left:0;">Price</div>
                        <div name="oldTotalPrice" ><%=df.format(oldTotalPrice)%> VNÐ</div>
                    </div>
                    <div class="Discount">
                        <div  style="padding-left:0;">Discount</div>
                        <div name="discount" ><%=df.format(oldTotalPrice-totalPrice)%> VNÐ</div>
                    </div>
                    <div class="totalPrice" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                        <div  class="">TOTAL PRICE</div>
                        <div name="totalPriceAll" value="<%=df.format(totalPrice)%>"><%=df.format(totalPrice)%>  VNÐ</div>
                    </div>
                    <a href="PlaceOrderURL?service=placeOrder&oldTotalPrice=<%=oldTotalPrice%>&Discount=<%=oldTotalPrice-totalPrice%>&totalPriceAll=<%=totalPrice%>"><button class="btn">CHECKOUT</button></a>
                </div>

            </div>
            <script>

                var quantity = document.getElementsByName("quantityValue");
                var subprice = document.getElementsByName("subPrice");
                var price = document.getElementsByName("Price");
                var quantityUpdate = document.getElementsByName("quantityUpdate");
                var totalPriceAll = document.getElementsByClassName("totalPriceAll");
                var sumTotal = 0;
                var sum = 0;
                for (let i = 0; i < quantity.length; i++) {
                    quantity[i].addEventListener("change", function () {
                        sum = parseFloat(subprice[i].getAttribute("value")) * parseFloat(quantity[i].value);
                        price[i].innerHTML = sum.toLocaleString("en-US") + "VNÐ";
                        quantityUpdate[i].value = quantity[i].value;
                    });
                }
                //            quantity[i].addEventListener("onchange", function () {
                //                sumTotal = parseFloat(subprice[i].getAttribute("value")) * parseFloat(quantity[i].value);
                //                price[i].innerHTML = sum.toLocaleString("en-US") + "VNÐ";
                //                quantityUpdate[i].value = quantity[i].value;
                //
                //            });
            </script>
    </body>
</html>
