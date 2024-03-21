/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Category;
import entity.Order;
import entity.Product;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCategory;
import model.DAOOrder;
import model.DAOProduct;
import model.DAOUser;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

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
        HttpSession session = request.getSession();
        boolean isLogged = session != null && session.getAttribute("account") != null;
        if (isLogged) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "order";
            }
            if (action.equals("product")) {
                DAOProduct dao = new DAOProduct();
                Vector<Product> vector = dao.getAllProduct("select * from Products");
                request.setAttribute("data", vector);
                request.getRequestDispatcher("/JSP/productManager.jsp").forward(request, response);
            } else if (action.equals("category")) {
                DAOCategory dao = new DAOCategory();
                Vector<Category> vector = dao.getAllData("select * from Categories");
                request.setAttribute("data", vector);
                request.getRequestDispatcher("/JSP/categoryManager.jsp").forward(request, response);
            } else if (action.equals("user")) {
                DAOUser dao = new DAOUser();
                Vector<User> vector = dao.getAllUser("select * from [User]");
                System.out.println(vector);
                request.setAttribute("data", vector);
                request.getRequestDispatcher("/JSP/userManager.jsp").forward(request, response);
            } else if (action.equals("order")) {
                DAOOrder dao = new DAOOrder();
                int TotalOrder=dao.countOrder("select count(*) from Orders");
                int OrderSucess=dao.countOrder("select count(*) from Orders where StatusID=2");
                int orderProcessing=dao.countOrder("select count(*) from Orders where StatusID=1");
                int OrderPending=dao.countOrder("select count(*) from Orders where StatusID=0");
                request.setAttribute("TotalOrder", TotalOrder);
                request.setAttribute("OrderSucess", OrderSucess);
                request.setAttribute("orderProcessing", orderProcessing);
                request.setAttribute("OrderPending", OrderPending);
                Vector<Order> vector = dao.getAllData("select * from Orders");
                request.setAttribute("data", vector);
                request.getRequestDispatcher("/JSP/orderManager.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("loginURL");
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
        doPost(request, response);
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
