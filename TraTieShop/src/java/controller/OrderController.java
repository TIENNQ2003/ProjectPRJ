/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.Order;
import entity.OrderDetail;
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
import java.sql.Date;
import java.util.Vector;
import model.DAOCart;
import model.DAOOrder;
import model.DAOOrderDetail;
import model.DAOProduct;

/**
 *
 * @author nqtie
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderURL"})
public class OrderController extends HttpServlet {

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

                String service = request.getParameter("service");
                DAOOrder dao = new DAOOrder();
                int TotalOrder = dao.countOrder("select count(*) from Orders");
                int OrderSucess = dao.countOrder("select count(*) from Orders where StatusID=2");
                int orderProcessing = dao.countOrder("select count(*) from Orders where StatusID=1");
                int OrderPending = dao.countOrder("select count(*) from Orders where StatusID=0");
                request.setAttribute("TotalOrder", TotalOrder);
                request.setAttribute("OrderSucess", OrderSucess);
                request.setAttribute("orderProcessing", orderProcessing);
                request.setAttribute("OrderPending", OrderPending);
                DAOOrderDetail dAOOrderDetail = new DAOOrderDetail();
                DAOProduct daopro = new DAOProduct();
                DAOCart daocart = new DAOCart();
                User user = (User) session.getAttribute("account");
                if (service == null) {
                    service = "listAllOrder";
                }
                if (service.equals("listAllOrder")) {
                    String message = request.getParameter("message");
                    if (message == null) {
                        message = "";
                    }
                    Vector<Order> vector = dao.getAllData("select * from Orders");
                    request.setAttribute("data", vector);
                    request.setAttribute("message", message);
                    RequestDispatcher dis = request.getRequestDispatcher("/JSP/orderManager.jsp");
                    dis.forward(request, response);
                }
                if (service.equals("addOrder")) {
                    String check = request.getParameter("check");
                    int OrderID = dao.getMaxID() + 1;
                    int userID = user.getUserID();
                    String phone = request.getParameter("phone");
                    String name = request.getParameter("name");
                    String address = request.getParameter("address");
                    String totalAmount_raw = request.getParameter("totalAmount");
                    String orderDate = dao.getDate("select getDate()");
                    double totalAmount;
                    try {
                        totalAmount = Double.parseDouble(totalAmount_raw);
                        Order newOrder = new Order(OrderID, userID, orderDate, totalAmount, 0, address, phone, name);
                        System.out.println(newOrder);
                        int n = dao.insertOrderByPrepared(newOrder);
                        if (check.equals("buyNow")) {
                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            int proId = Integer.parseInt(request.getParameter("proID"));
                            Product pro = daopro.getProductByID(proId);
//                        OrderDetail newOrderDetail = new OrderDetail(OrderID, proId, quantity, pro.getUnitPrice() * (1 - pro.getDiscount() / 100) * quantity);
                            dAOOrderDetail.insertOrderDetailByPrepared(new OrderDetail(OrderID, proId, quantity, pro.getUnitPrice() * (1 - pro.getDiscount() / 100) * quantity));
                        } else {
                            java.util.Enumeration em = session.getAttributeNames();
                            while (em.hasMoreElements()) {
                                String id = em.nextElement().toString(); //get key
                                if (!id.equals("account")) {
                                    Cart cart = (Cart) session.getAttribute(id); //get value
                                    int proid = Integer.parseInt(id);
                                    Product pro = daopro.getProductByID(proid);
                                    dAOOrderDetail.insertOrderDetailByPrepared(new OrderDetail(OrderID, proid, cart.getQuantity(), pro.getUnitPrice() * (1 - pro.getDiscount() / 100) * cart.getQuantity()));
                                    session.removeAttribute(id);
                                    daocart.deleteCart(userID, proid);
                                }
                            }
                        }

                        response.sendRedirect("home");
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }

                }

//            if (service.equals("deleteProduct")) {
//                int pid = Integer.parseInt(request.getParameter("pid"));
//                int n = dao.deleteProduct(pid);
//                String st = "";
//                if (n > 0) {
//                    st = "delete success";
//                } else {
//                    st = "can't delete because....";
//                }
//                response.sendRedirect("ProductURL?message=" + st);
//            }
                if (service.equals("updateOrderFromAdmin")) {
                    String statusID_raw = request.getParameter("statusID");
                    String oid_raw = request.getParameter("oid");
                    int statusID, oid;
                    String st = "";
                    try {
                        String requiredDate = dao.getDate("select getDate()+3");
                        String shipdate=dao.getDate("select getDate()");
                        statusID = Integer.parseInt(statusID_raw);
                        if(statusID==2){
                            oid = Integer.parseInt(oid_raw);
                            int n = dao.updateStatusFromAdmin(shipdate, statusID, oid);
                        if (n > 0) {
                            st = "Update success";
                        } else {
                            st = "can't update because....";
                        }
                        response.sendRedirect("OrderURL?message=" + st);
                        }else{
                            oid = Integer.parseInt(oid_raw);
                            int n = dao.updateStatusFromAdmin(requiredDate, statusID, oid);
                        if (n > 0) {
                            st = "Update success";
                        } else {
                            st = "can't update because....";
                        }
                        response.sendRedirect("OrderURL?message=" + st);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                }
                if (service.equals("updateOrderFromUser")) {
                    String statusID_raw = request.getParameter("statusID");
                    String oid_raw = request.getParameter("oid");
                    int statusID, oid;
                        String shippeddate = dao.getDate("select getDate()");
                        statusID = Integer.parseInt(statusID_raw);
                        oid = Integer.parseInt(oid_raw);
                        Order order=dao.getOrderByOID(oid);
                        if(order.getStatusID()==1){
                             int n = dao.updateStatusFromUser(shippeddate, statusID, oid);
                        String st = "";
                        if (n > 0) {
                            st = "Update success";
                        } else {
                            st = "can't update because....";
                        }
                        }
                        response.sendRedirect("MyPurchaseURL");
                }
                if (service.equals("Search")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        Vector<Order> vector = dao.getAllData("select * from Orders");
                        request.setAttribute("data", vector);
                        request.getRequestDispatcher("/JSP/orderManager.jsp").forward(request, response);
                    } else {
                        String status_raw = request.getParameter("status");
                        String DateFrom = request.getParameter("DateFrom");
                        String DateTo = request.getParameter("DateTo");
                        String sql = "select * from [Orders] where 1=1";
                        if (status_raw != null && !status_raw.equals("")) {
                            int status = Integer.parseInt(status_raw);
                            sql += " and StatusID=" + status;
                        }
                        if (DateFrom != null && !DateFrom.equals("")) {
                            sql += " and OrderDate>='" + DateFrom + "'";
                        }
                        if (DateTo != null && !DateTo.equals("")) {
                            sql += " and OrderDate<='" + DateTo + "'";
                        }
                        Vector<Order> vector = dao.getAllData(sql);
                        System.out.println(sql);
                        System.out.println(vector);
                        request.setAttribute("data", vector);
                        request.getRequestDispatcher("/JSP/orderManager.jsp").forward(request, response);
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
