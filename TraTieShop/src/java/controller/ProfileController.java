/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.User;
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
@WebServlet(name = "ProfileController", urlPatterns = {"/ProfileURL"})
public class ProfileController extends HttpServlet {

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
            HttpSession session = request.getSession();
            boolean isLogged = session != null && session.getAttribute("account") != null;
            if (isLogged) {
                String service = request.getParameter("service");
                DAOUser dao = new DAOUser();
                if (service.equals("updateProfile")) {
                    String UserId_raw = request.getParameter("id");
                    String FullName = request.getParameter("FullName");
                    String Address = request.getParameter("Address");
                    String Phone = request.getParameter("Phone");
                    String Gender_raw = request.getParameter("Gender");
                    String Email = request.getParameter("Email");
                    String UserName = request.getParameter("UserName");
                    String PassWord = request.getParameter("PassWord");
                    String RoleID_raw = request.getParameter("RoleID");
                    int Gender, UserId;
                    try {
                        int roleID = Integer.parseInt(RoleID_raw);
                        UserId = Integer.parseInt(UserId_raw);
                        Gender = Integer.parseInt(Gender_raw);
                        User userUpdate = new User(UserId, FullName, Address, Phone, Gender, Email, UserName, PassWord, roleID);
                        int n = dao.updateProfile(userUpdate);
                        session.setAttribute("account", userUpdate);
                        String st = "";
                        if (n > 0) {
                            st = "Update success";
                        } else {
                            st = "can't update because....";
                        }
                        request.setAttribute("message", st);
                        request.getRequestDispatcher("/JSP/profile.jsp").forward(request, response);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
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
        HttpSession session = request.getSession();
        boolean isLogged = session != null && session.getAttribute("account") != null;
        if (isLogged) {
            request.getRequestDispatcher("/JSP/profile.jsp").forward(request, response);
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
