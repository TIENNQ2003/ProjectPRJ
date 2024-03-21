<%-- 
    Document   : addCategory
    Created on : Oct 5, 2023, 2:54:01 PM
    Author     : nqtie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="fonts/fontawesome-free-6.4.0-web/css/all.css">
          <link rel="stylesheet" href="CSS/add.css">
    </head>
    <body>
        <div class="form">
            <h1 class="form_title">Add new a Category</h1>
            <form class="form_info" action="CategoryURL" method="post" style="border: 2px;">
                <input type="hidden" name="service" value="addCategory" />
                <table class="form_layout">
                    <tr class="form_detail">
                        <td class="input_title">Enter CategoryID:</td>
                        <td class="input_info"><input type="number" name="CategoryID" /></br></td>
                    </tr>
                    <tr class="form_detail">
                        <td class="input_title">Enter CategoryName:</td>
                        <td class="input_info"><input type="text" name="CategoryName" /></br></td>
                    </tr>
                    <tr class="button">
                        <td><input type="reset" value="CLEAR" /></td>
                        <td><input type="submit" name="submit" value="ADD" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
