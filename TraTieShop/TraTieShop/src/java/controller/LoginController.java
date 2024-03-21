/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCart;
import model.DAOUser;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/loginURL"})
public class LoginController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String Username = request.getParameter("Username");
            String Password = request.getParameter("Password");
            DAOUser d = new DAOUser();
            DAOCart daocart = new DAOCart();
            User userLogin = d.checkLogin(Username, Password);
            HttpSession session = request.getSession();
            if (userLogin == null) {
                //chua co
                request.setAttribute("error", "Username or passwors invalid!!!");
                request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
            } else {
                //co tim thay
                //tao session
                Vector<Cart> vector = daocart.getAllData("select * from cart where UserID=" + userLogin.getUserID());
                session.setAttribute("account", userLogin);
                for (Cart cart : vector) {
                    int n = daocart.deleteCartOverDate(cart);
                    if (n == 0) {
                        session.setAttribute(String.valueOf(cart.getProductID()), cart);
                    }

                }
                //role=1-admin
                if (userLogin.getRoleID() == 1) {
                    response.sendRedirect("admin");
                } else {
                    response.sendRedirect("home");
                }
                //roll 2 -welcome
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
        request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
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
