/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOProduct;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "PlaceOrderController", urlPatterns = {"/PlaceOrderURL"})
public class PlaceOrderController extends HttpServlet {

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
            out.println("<title>Servlet PlaceOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PlaceOrderController at " + request.getContextPath() + "</h1>");
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
        String Service = request.getParameter("service");
        double oldTotalPrice = 0;
        double Discount = 0;
        double totalPriceAll = 0;
        if (Service.equals("buyNow")) {
            int proID = Integer.parseInt(request.getParameter("proId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            DAOProduct dao = new DAOProduct();
            Product pro = dao.getProductByID(proID);
            oldTotalPrice = pro.getUnitPrice() * quantity;
            Discount = pro.getUnitPrice() * quantity - pro.getUnitPrice() * (1 - pro.getDiscount() / 100) * quantity;
            totalPriceAll = pro.getUnitPrice() * (1 - pro.getDiscount() / 100) * quantity;
            request.setAttribute("proID", proID);
            request.setAttribute("quantity", quantity);
        } else {
            oldTotalPrice = Double.parseDouble(request.getParameter("oldTotalPrice"));
            Discount = Double.parseDouble(request.getParameter("Discount"));
            totalPriceAll = Double.parseDouble(request.getParameter("totalPriceAll"));
        }
        request.setAttribute("check", Service);
        request.setAttribute("oldTotalPrice", oldTotalPrice);
        request.setAttribute("Discount", Discount);
        request.setAttribute("totalPriceAll", totalPriceAll);
        request.getRequestDispatcher("/JSP/placeOrders.jsp").forward(request, response);
    }else{
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
