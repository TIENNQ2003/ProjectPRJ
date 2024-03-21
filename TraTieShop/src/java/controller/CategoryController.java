/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Category;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategory;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryURL"})
public class CategoryController extends HttpServlet {

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
            String service = request.getParameter("service");
            DAOCategory dao = new DAOCategory();

            if (service == null) {
                service = "listAllCategory";
            }
            if (service.equals("listAllCategory")) {
                String message = request.getParameter("message");
                if (message == null) {
                    message = "";
                }
                Vector<Category> vector = dao.getAllData("select * from Categories");
                request.setAttribute("data", vector);
                request.setAttribute("message", message);
                RequestDispatcher dis = request.getRequestDispatcher("/JSP/categoryManager.jsp");
                dis.forward(request, response);
            }
            if (service.equals("addCategory")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/JSP/addCategory.jsp").forward(request, response);
                } else {
                    String CategoryID_raw = request.getParameter("CategoryID");
                    String CategoryName = request.getParameter("CategoryName");

                    int CategoryID;
                    try {

                        CategoryID = Integer.parseInt(CategoryID_raw);
                        Category catenew = new Category(CategoryID, CategoryName);
                        int n = dao.insertCategoriesByPrepared(catenew);
                        String st = "";
                        if (n > 0) {
                            st = "Insert success";
                        } else {
                            st = "can't Insert because....";
                        }
                        response.sendRedirect("CategoryURL?message=" + st);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }

                }
            }
            if (service.equals("deleteCategory")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                int n = dao.deleteCategory(cid);
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because....";
                }
                response.sendRedirect("CategoryURL?message=" + st);
            }
            if (service.equals("updateCategory")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int cid = Integer.parseInt(request.getParameter("cid"));
                    Vector<Category> vector = dao.getAllData("select * from Categories\n"
                            + "where CategoryID=" + cid);
                    request.setAttribute("data", vector);
                    request.getRequestDispatcher("/JSP/updateCategory.jsp").forward(request, response);
                } else {
                    String CategoryID_raw = request.getParameter("CategoryID");
                    String CategoryName = request.getParameter("CategoryName");
                    String oldCid_raw = request.getParameter("oldCid");
                    int CategoryID, oldCid;
                    try {
                        oldCid = Integer.parseInt(oldCid_raw);
                        CategoryID = Integer.parseInt(CategoryID_raw);
                        Category catenew = new Category(CategoryID, CategoryName);
                        System.out.println(oldCid);
                        System.out.println(catenew);
                        int n = dao.updateCategory(catenew, oldCid);
                        String st = "";
                        if (n > 0) {
                            st = "Update success";
                        } else {
                            st = "can't update because....";
                        }
                        response.sendRedirect("CategoryURL?message=" + st);

                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                }
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
