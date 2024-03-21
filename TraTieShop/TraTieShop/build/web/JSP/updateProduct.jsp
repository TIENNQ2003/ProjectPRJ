<%-- 
    Document   : updateProduct
    Created on : Oct 17, 2023, 11:36:45 AM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector"%>
<%@page import="entity.Product,model.DAOCategory" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="fonts/fontawesome-free-6.4.0-web/css/all.css">
        <link rel="stylesheet" href="CSS/add.css">
    </head>
    <body>
         <%
            Vector<Product> vector=(Vector<Product>)request.getAttribute("data");
            Product product=vector.get(0);
             ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
        %>
        <div class="form">
            <h1 class="form_title">Update new a Product</h1>
            <form class="form_info" action="ProductURL" method="post" style="border: 2px;">
                <input type="hidden" name="service" value="updateProduct" />
                <input type="hidden" name="ProductID" value="<%=product.getProductId()%>" />
                <table class="form_layout">
                    <tr class="form_detail">
                        <td class="input_title">ProductID:</td>
                        <td> <%=product.getProductId()%></br></td>
                    </tr>
                    <tr class="form_detail">
                        <td class="input_title">Enter ProductName:</td>
                        <td class="input_info"><input type="text" value="<%=product.getProductName()%>" name="ProductName" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter CategoryID:</td>
                        <td class="input_info"><select name="CategoryID">
                               <%while(rsCate.next()){%>
                                <option value="<%=rsCate.getInt(1)%>"<%=(product.getCategoryID() == rsCate.getInt(1)
                        ? "selected":"")%> ><%=rsCate.getString(2)%></option>
                                <%}%>
                            </select></br></td>
                    </tr >
                    <tr  class="form_detail">
                        <td class="input_title">Enter Description:</td>
                        <td class="input_info"><input type="text" value="<%=product.getDescription()%>" name="Descrsiption" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter UnitPrice:</td>
                        <td class="input_info"> <input type="number" value="<%=product.getUnitPrice()%>" name="UnitPrice" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter UnitinStock:</td>
                        <td class="input_info"><input type="number" value="<%=product.getUnitinStock()%>" name="UnitinStock" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter Discount:</td>
                        <td class="input_info"><input type="number" value="<%=product.getDiscount()%>" name="Discount" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title"> Enter Status:</td>
                        <td class="input_info_radio">
                            <input type="radio" name="Status" value="1"<%=(product.getStatus() == 1 
                        ? "checked":"")%> />continued
                            <input type="radio" name="Status" value="0"<%=(product.getStatus() == 0 
                        ? "checked":"")%> />discontinued
                            </br>
                        </td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title"> Enter Image:</td>
                        <td class="input_info"> <input type="file"  name="Image" /></br></td>
                    </tr>
                    <tr class="button">
                        <td><input type="reset" value="CLEAR" /></td>
                        <td><input type="submit" name="submit" value="Update" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
