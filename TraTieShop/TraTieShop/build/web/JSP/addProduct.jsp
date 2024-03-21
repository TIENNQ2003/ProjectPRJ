<%-- 
    Document   : addProduct
    Created on : Oct 5, 2023, 2:53:46 PM
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
            <h1 class="form_title">Add new a Product</h1>
            <form class="form_info" action="ProductURL" method="post" style="border: 2px;">
                <input type="hidden" name="service" value="addProduct" />
                <table class="form_layout">
                    <tr class="form_detail">
                        <td class="input_title">Enter ProductID:</td>
                        <td class="input_info"><input type="number" name="ProductID" /></br></td>
                    </tr>
                    <tr class="form_detail">
                        <td class="input_title">Enter ProductName:</td>
                        <td class="input_info"><input type="text" name="ProductName" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter CategoryID:</td>
                        <td class="input_info"><select name="CategoryID">
                                <option value="1">Nike</option>
                                <option value="2">Adidas</option>
                                <option value="3">Vans</option>
                                <option value="4">MLB</option>
                                <option value="5">Converse</option>
                                <option value="6">Puma</option>
                                <option value="7">Phụ Kiện</option>
                            </select></br></td>
                    </tr >
                    <tr  class="form_detail">
                        <td class="input_title">Enter Description:</td>
                        <td class="input_info"><input type="text" name="Descrsiption" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter UnitPrice:</td>
                        <td class="input_info"> <input type="number" name="UnitPrice" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter UnitinStock:</td>
                        <td class="input_info"><input type="number" name="UnitinStock" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title">Enter Discount:</td>
                        <td class="input_info"><input type="number" name="Discount" /></br></td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title"> Enter Status:</td>
                         <td class="input_info_radio">
                            <input type="radio" name="Status" value="1" />continued
                            <input type="radio" name="Status" value="0" />discontinued
                            </br>
                        </td>
                    </tr>
                    <tr  class="form_detail">
                        <td class="input_title"> Enter Image:</td>
                        <td class="input_info"> <input type="file" name="Image" /></br></td>
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
