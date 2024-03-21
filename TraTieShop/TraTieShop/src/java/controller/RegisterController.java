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
import model.DAOUser;

/**
 *
 * @author nqtie
 */
@WebServlet(name="RegisterController", urlPatterns={"/registerURL"})
public class RegisterController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            DAOUser dao = new DAOUser();
           if (service.equals("addUser")) {
                String submit = request.getParameter("submit");
                
                int UserID = dao.getMaxID() + 1;
                System.out.println(UserID);
                String Fullname = request.getParameter("Fullname");
                String Address = request.getParameter("Address");
                String Phone = request.getParameter("Phone");
                String gender_raw = request.getParameter("Gender");
                String Email = request.getParameter("Email");
                String Username = request.getParameter("Username");
                String Password = request.getParameter("Password");
                User usertest = dao.checkRegister(Email, Username);
                String st="";
                if (usertest == null) {
                    int gender;
                    try {
                        gender = Integer.parseInt(gender_raw);
                        User userNew = new User(UserID, Fullname, Address, Phone, gender, Email, Username, Password, 2);
                        int n=dao.insertUserByPrepared(userNew);
                        if(n>0){
                            st = "Register sucess!!!";
                        }else{
                            st="can't register because.....";
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                } else {
                    st = "Username or Email Existed!!!";
                }
                request.setAttribute("message", st);
                request.getRequestDispatcher("/JSP/register.jsp").forward(request, response);

            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/register.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
