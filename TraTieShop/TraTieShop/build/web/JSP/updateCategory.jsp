<%-- 
    Document   : updateCategory
    Created on : Oct 17, 2023, 2:21:35 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Category" %>
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
             Vector<Category> vector=(Vector<Category>)request.getAttribute("data");
            Category category=vector.get(0);
        %>
        <div class="form">
            <h1 class="form_title">Add new a Category</h1>
            <form class="form_info" action="CategoryURL" method="post" style="border: 2px;">
                <input type="hidden" name="service" value="updateCategory" />
                <input type="hidden" name="oldCid" value="<%=category.getCategoryID()%>" />
                <table class="form_layout">
                    <tr class="form_detail">
                        <td class="input_title">Enter CategoryID:</td>
                        <td class="input_info"><input type="number" value="<%=category.getCategoryID()%>" name="CategoryID" /></br></td>
                    </tr>
                    <tr class="form_detail">
                        <td class="input_title">Enter CategoryName:</td>
                        <td class="input_info"><input type="text" value="<%=category.getCategoryName()%>" name="CategoryName" /></br></td>
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
