/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOUser;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "UserController", urlPatterns = {"/userURL"})
public class UserController extends HttpServlet {

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
            String service = request.getParameter("service");
            DAOUser dao = new DAOUser();

            if (service == null) {
                service = "listAllUser";
            }
            if (service.equals("listAllUser")) {
                String message = request.getParameter("message");
                if (message == null) {
                    message = "";
                }
                Vector<User> vector = dao.getAllUser("select * from [user]");
                request.setAttribute("data", vector);
                request.setAttribute("message", message);
                RequestDispatcher dis = request.getRequestDispatcher("/JSP/userManager.jsp");
                dis.forward(request, response);
            }

            if (service.equals("deleteUser")) {
                int id = Integer.parseInt(request.getParameter("id"));
                int roleID = Integer.parseInt(request.getParameter("roleID"));
                int n = 0;
                if (roleID == 2) {
                    n = dao.deleteUser(id);
                }
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because....";
                }
                response.sendRedirect("admin?action=user");
            }
//           
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
