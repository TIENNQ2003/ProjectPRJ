<%-- 
    Document   : ProductInfoDetailURL
    Created on : Oct 18, 2023, 11:17:56 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/productInfoDetail.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="headerHome.jsp" %>
        <div class="productInfo2">
            <div class="URLProduct">
                <a href="home">Home</a>
                <c:if test="${requestScope.cate!=null}">
                    <a href="#"> / ${requestScope.cate.categoryName}</a></c:if>
                <a> / ${requestScope.product.getProductName()}</a>
            </div>
            <div class="productInfoDetail">
                <div class="productImage">
                    <div class="main_image">
                        <img id="main_image" src="images/Product/${requestScope.product.getProductId()}/1.png">
                    </div>
                    <div class="sub_image">
                        <img onclick="changeImage(this)" src="images/Product/${requestScope.product.getProductId()}/1.png">
                        <img onclick="changeImage(this)" src="images/Product/${requestScope.product.getProductId()}/2.png">
                        <img onclick="changeImage(this)" src="images/Product/${requestScope.product.getProductId()}/3.png">
                        <img onclick="changeImage(this)" src="images/Product/${requestScope.product.getProductId()}/4.png">
                    </div>
                    <script>
                        function changeImage(img) {
                            var main_image = document.getElementById("main_image");
                            main_image.src = img.src;
                        }
                    </script>
                </div>
                <div class="productContent">
                    <p class="ProductName">${requestScope.product.getProductName()}</h3></br>
                    <p class="ProductPrice"><span class="newPrice">${requestScope.product.getUnitPrice()*(1-requestScope.product.getDiscount()/100)} VNÐ</span><span class="oldprice">${requestScope.product.getUnitPrice()} VNÐ </span><span class="discount">${requestScope.product.getDiscount()}% off</span></p>
                    <p class="ProductID">Mã sản phẩm: ${requestScope.product.getProductId()}</p>
                    <p>Thương hiệu: ${requestScope.CategoryName}</p>
                    <p>Mô Tả: ${requestScope.product.getDescription()}</p>
                    <div class="shipping"><span>Giao Hàng: </span><p class="shipping_info">Free shipping</br>Free shipping for orders over ₫99.000</p></div>
                    <div class="form_flex">
                        <form class="form_product"  action=${sessionScope.account!=null?"AddToCartURL":"loginURL"} >
                            <input type="hidden" name="pid" value="${requestScope.product.getProductId()}"/>
                            <div class="quantity">
                                <span class="quantity_header">Quantity:</span>
                                <button id="buttondesc" type="button" class="button">-</button>
                                <input id="quantityValue" type="text" value="1" name="quantity">
                                <button id="buttonasc" type="button" class="button">+</button>
                                <span style="color: grey;font-size: 10px;">x${requestScope.product.getUnitinStock()}</span>
                                <span style="color: red;">${requestScope.error}</span>
                            </div>
                            <button class="buttonSubmit1 " type="submit">Add To Card</button>
                        </form>
                        <form action="${sessionScope.account!=null?"PlaceOrderURL":"loginURL"}" class="formBuyNow" >
                            <input type="hidden"/>
                            <input  type="hidden" name="proId" value="${requestScope.product.getProductId()}"/>
                            <input id="quantityBuyNow" type="hidden" name="quantity" value="1"/>
                            <input type="hidden" name="service" value="buyNow"/>
                            <button class="buttonSubmit2" type="submit">Buy Now</button>
                        </form>
                    </div>


                </div>
            </div>
        </div>
        <div id="listProductInOrderDeatil">
            <div class="productHeader">
                <h3>Sản phẩm tương tự</h3>
            </div>
            <ul class="productInfo">
                <c:forEach items="${requestScope.data}" var="p">
                    <li>
                        <a href="ProductInfoDetailURL?pid=${p.getCategoryID()}&cid=${p.getCategoryID()}">
                            <img src="${p.getImage()}"/>
                            <p>${p.getProductName()}</p>
                            <p class="oldPrice">Gia goc: <span class="old" >${p.getUnitPrice()}</span>VNÐ<span class="discount">${p.getDiscount()}%</span></p>
                            <p>sale: ${p.getUnitPrice()*(1-p.getDiscount()/100)}VNÐ</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <script>
            var buttonasc = document.getElementById("buttonasc");
            var buttondesc = document.getElementById("buttondesc");
            var quantity = document.getElementById("quantityValue");
            var quantityBuyNow = document.getElementById("quantityBuyNow");
            buttonasc.addEventListener("click", function () {
                quantity.value++;
                quantityBuyNow.value++;
                console.log(quantityBuyNow.value);
            });
            buttondesc.addEventListener("click", function () {
                if (quantity.value > 1) {
                    quantity.value--;
                    quantityBuyNow.value--;
                    console.log(quantityBuyNow.value);
                }
            });

        </script>
    </body>
</html>
