/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOUser;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "UpdateNewPasServlet", urlPatterns = {"/updatepassword"})
public class UpdatePasswordController extends HttpServlet {

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
                DAOUser dao = new DAOUser();
                int id = Integer.parseInt(request.getParameter("id"));
                String newpass = request.getParameter("newpass");
                String confirmnewpass = request.getParameter("confirmnewpass");
                if (newpass.equals(confirmnewpass)) {
                    int n = dao.changePassWord(id, newpass);
                    session.setAttribute("account", dao.getUserByID(id));
                    String st = "";
                    if (n > 0) {
                        st = "Update success";
                    } else {
                        st = "can't update because....";
                    }
                    request.setAttribute("message", st);
                    request.getRequestDispatcher("/JSP/updateNewPass.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        boolean isLogged = session != null && session.getAttribute("account") != null;
        if (isLogged) {
            request.getRequestDispatcher("/JSP/updateNewPass.jsp").forward(request, response);
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
