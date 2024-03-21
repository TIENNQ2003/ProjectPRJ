/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOCart;
import model.DAOProduct;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCartURL"})
public class AddToCart extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        boolean isLogged = session != null && session.getAttribute("account") != null;
        if (isLogged) {
            String pid = request.getParameter("pid");
            String quantity_raw = request.getParameter("quantity");
            DAOCart dao = new DAOCart();
            Cart cart = (Cart) session.getAttribute(pid);
            User user = (User) session.getAttribute("account");
            int proid = Integer.parseInt(pid);
            DAOProduct daopro = new DAOProduct();
            Product pro = daopro.getProductByID(proid);
            int quantity = Integer.parseInt(quantity_raw);
            String dateInsert = dao.getDate("select getdate()");
            if (quantity > pro.getUnitinStock()) {
                String mes = "Invalid quantity";
                response.sendRedirect("ProductInfoDetailURL?pid=" + proid + "&cid=" + pro.getCategoryID() + "&error=" + mes);
            } else {
                if (cart == null) {
                    Cart cartNew = new Cart(user.getUserID(), proid, quantity, dateInsert);
                    session.setAttribute(pid, cartNew);
                    dao.insertCartByPrepared(cartNew);
                    response.sendRedirect("ProductInfoDetailURL?pid=" + proid + "&cid=" + pro.getCategoryID());
                } else {
                    Cart cartNew = new Cart(user.getUserID(), proid, cart.getQuantity() + quantity, dateInsert);
                    cart.setQuantity(cart.getQuantity() + quantity);
                    session.setAttribute(pid, cartNew);
                    dao.updateCart(cartNew);
                    response.sendRedirect("ProductInfoDetailURL?pid=" + proid + "&cid=" + pro.getCategoryID());
                }
            }
        } else {
            response.sendRedirect("loginURL");
        }

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
