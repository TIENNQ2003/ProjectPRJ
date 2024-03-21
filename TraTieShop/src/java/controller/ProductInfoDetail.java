/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategory;
import model.DAOProduct;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "ProductInfoDetail", urlPatterns = {"/ProductInfoDetailURL"})
public class ProductInfoDetail extends HttpServlet {

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
            out.println("<title>Servlet ProductInfoDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductInfoDetail at " + request.getContextPath() + "</h1>");
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
        String error=request.getParameter("error");
        if(error!=null){
            request.setAttribute("error", error);
        }
        DAOCategory daocate = new DAOCategory();
        DAOProduct dao = new DAOProduct();
        String cid_raw = request.getParameter("cid");
        if (cid_raw != null) {
            int cid = Integer.parseInt(cid_raw);
            Category cate = daocate.getCategoryByCID(cid);
            request.setAttribute("cate", cate);
        }
        System.out.println(cid_raw);
        Vector<Product> vector = dao.getAllProduct("select top(4) * from Products where CategoryID='" + cid_raw + "' order by UnitPrice desc");
        System.out.println(vector);
        request.setAttribute("data", vector);
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product product = dao.getProductByID(pid);
        request.setAttribute("CategoryName", daocate.getCategoryByCID(product.getCategoryID()).getCategoryName());
        request.setAttribute("product", product);
        request.getRequestDispatcher("/JSP/productInfoDetail.jsp").forward(request, response);
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
