<%-- 
    Document   : displayProduct
    Created on : Oct 5, 2023, 4:39:05 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <head>
         
        <%
//            String pageTitle = (String)request.getAttribute("pageTitle");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.pageTitle}</title>
    </head>
    <body>
        <%
//        Vector<Product> vector = (Vector<Product>)request.getAttribute("data");
//        String tableTitle = (String) request.getAttribute("tableTitle");
//        String message =(String) request.getAttribute("message");
//        DAOSuppliers daosup=new DAOSuppliers();
//        DAOCategories daocate=new DAOCategories();
        %>
        
        
        <table>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>CategoryID</th>
                <th>Description</th>
                <th>UnitPrice</th>
                <th>UnitinStock</th>
                <th>Discount</th>
                <th>Status</th>
                <th>Image</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%
//            for (Product product : vector) {
            %>
            <c:forEach var="i" items="${requestScope.data}">
                <tr>
                <td>  ${i.getProductId()}</td>
                <td>   ${i.getProductName()}</td>
                <td>   ${i.getCategoryID()}</td>
                <td>   ${i.getDescription()}</td>
                <td>   ${i.getUnitPrice()}</td>
                <td>   ${i.getUnitinStock()}</td>
                <td>   ${i.getDiscount()}</td>
                <td>   ${i.getStatus()}</td>
                <td>   ${i.getImage()}</td>
                <td>update</td>
            </tr>
            </c:forEach>
            
            <%
//                }
            %>
        </table>
    </body>
</html>
