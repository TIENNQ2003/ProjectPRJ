/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOCategory;
import model.DAOProduct;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            boolean isLogged = session != null && session.getAttribute("account") != null;
            if (isLogged) {
                String service = request.getParameter("service");
                DAOProduct dao = new DAOProduct();

                if (service == null) {
                    service = "listAllProduct";
                }
                if (service.equals("listAllProduct")) {
                    String message = request.getParameter("message");
                    if (message == null) {
                        message = "";
                    }
                    Vector<Product> vector = dao.getAllProduct("select * from Products");
                    request.setAttribute("data", vector);
                    request.setAttribute("message", message);
                    RequestDispatcher dis = request.getRequestDispatcher("JSP/productManager.jsp");
                    dis.forward(request, response);
                }
                if (service.equals("addProduct")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        request.getRequestDispatcher("JSP/addProduct.jsp").forward(request, response);
                    } else {
                        String ProductID_raw = request.getParameter("ProductID");
                        String ProductName = request.getParameter("ProductName");
                        String CategoryID_raw = request.getParameter("CategoryID");
                        String Descrsiption = request.getParameter("Descrsiption");
                        String UnitPrice_raw = request.getParameter("UnitPrice");
                        String UnitinStock_raw = request.getParameter("UnitinStock");
                        String Discount_raw = request.getParameter("Discount");
                        String Status_raw = request.getParameter("Status");
                        String Image = "images/Product/" + ProductID_raw + "/" + request.getParameter("Image");
                        int ProductID, CategoryID, UnitinStock, Status;
                        double UnitPrice, Discount;
                        if (ProductName == null) {

                        }
                        try {
                            ProductID = Integer.parseInt(ProductID_raw);
                            CategoryID = Integer.parseInt(CategoryID_raw);
                            UnitPrice = Double.parseDouble(UnitPrice_raw);
                            UnitinStock = Integer.parseInt(UnitinStock_raw);
                            Discount = Double.parseDouble(Discount_raw);
                            Status = Integer.parseInt(Status_raw);

                            Product proNew = new Product(ProductID, ProductName, CategoryID, Descrsiption, UnitPrice, UnitinStock, Discount, Status, Image);
                            int n = dao.insertProductByPrepared(proNew);
                            String st = "";
                            if (n > 0) {
                                st = "Insert success";
                            } else {
                                st = "can't Insert because....";
                            }
                            response.sendRedirect("ProductURL?message=" + st);
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                        }

                    }
                }
                if (service.equals("deleteProduct")) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    int n = dao.deleteProduct(pid);
                    String st = "";
                    if (n > 0) {
                        st = "delete success";
                    } else {
                        st = "can't delete because....";
                    }
                    response.sendRedirect("ProductURL?message=" + st);
                }
                if (service.equals("updateProduct")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        int pid = Integer.parseInt(request.getParameter("pid"));
                        Vector<Product> vector = dao.getAllProduct("select * from Products where ProductID=" + pid);
                        DAOCategory daocate = new DAOCategory();
                        ResultSet rsCate = daocate.getData("select CategoryID,CategoryName from Categories");
                        request.setAttribute("rsCate", rsCate);
                        request.setAttribute("data", vector);
                        request.getRequestDispatcher("/JSP/updateProduct.jsp").forward(request, response);
                    } else {
                        String ProductID_raw = request.getParameter("ProductID");
                        String ProductName = request.getParameter("ProductName");
                        String CategoryID_raw = request.getParameter("CategoryID");
                        String Descrsiption = request.getParameter("Descrsiption");
                        String UnitPrice_raw = request.getParameter("UnitPrice");
                        String UnitinStock_raw = request.getParameter("UnitinStock");
                        String Discount_raw = request.getParameter("Discount");
                        String Status_raw = request.getParameter("Status");
                        String Image = "images/Product/" + ProductID_raw + "/" + request.getParameter("Image");
                        int ProductID, CategoryID, UnitinStock, Status;
                        double UnitPrice, Discount;
                        if (ProductName == null) {

                        }
                        try {
                            ProductID = Integer.parseInt(ProductID_raw);
                            CategoryID = Integer.parseInt(CategoryID_raw);
                            UnitPrice = Double.parseDouble(UnitPrice_raw);
                            UnitinStock = Integer.parseInt(UnitinStock_raw);
                            Discount = Double.parseDouble(Discount_raw);
                            Status = Integer.parseInt(Status_raw);

                            Product proNew = new Product(ProductID, ProductName, CategoryID, Descrsiption, UnitPrice, UnitinStock, Discount, Status, Image);
                            int n = dao.updateProduct(proNew);
                            String st = "";
                            if (n > 0) {
                                st = "Update success";
                            } else {
                                st = "can't update because....";
                            }
                            response.sendRedirect("ProductURL?message=" + st);

                        } catch (NumberFormatException e) {
                            System.out.println(e);
                        }
                    }
                }
                if (service.equals("Search")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        Vector<Product> vector = dao.getAllProduct("select * from Products");
                        request.setAttribute("data", vector);
                        request.getRequestDispatcher("/JSP/productManager.jsp").forward(request, response);
                    } else {
                        String namesearch = request.getParameter("namesearch");
                        String orderPrice = request.getParameter("orderPrice");
                        String PriceFrom = request.getParameter("PriceFrom");
                        String PriceTo = request.getParameter("PriceTo");
                        String sql = " select* from Products where 1=1";
                        if (namesearch != null) {
                            sql += " AND [ProductName] like '%" + namesearch + "%' ";
                        }
                        if (PriceFrom != "") {
                            sql += " and UnitPrice>=" + PriceFrom;
                        }
                        if (PriceTo != "") {
                            sql += " and UnitPrice<=" + PriceTo;
                        }
                        if (orderPrice != null) {
                            sql += " order by UnitPrice " + orderPrice;
                        }

                        Vector<Product> vector = dao.getAllProduct(sql);
                        request.setAttribute("data", vector);
                        request.getRequestDispatcher("/JSP/productManager.jsp").forward(request, response);
                    }
                }
            } else {
                response.sendRedirect("loginURL");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
