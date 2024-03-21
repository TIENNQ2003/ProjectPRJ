/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order;
import entity.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nqtie
 */
public class DAOOrder extends DBConnect {

    public int insertOrderByPrepared(Order order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([OrderID]\n"
                + "           ,[UserID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[TotalPrice]\n"
                + "           ,[StatusID]\n"
                + "           ,[DeliverAddress]\n"
                + "           ,[Phone]\n"
                + "           ,[ReceiverName])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order.getOrderID());
            pre.setInt(2, order.getUserID());
            pre.setString(3, order.getOrderDate());
            pre.setDouble(4, order.getTotalPrice());
            pre.setInt(5, order.getStatusID());
            pre.setString(6, order.getDeliverAddress());
            pre.setString(7, order.getPhone());
            pre.setString(8, order.getReceiverName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStatusFromAdmin(String shippedDate, int statusID, int oid) {
        int n = 0;
        Order order = getOrderByOID(oid);
        if (order.getStatusID() == 2) {
            return n;
        } else if (statusID == 0) {
            String sql = "UPDATE [dbo].[Orders]\n"
                    + "   SET [RequireDate]=?\n"
                    + "       ,[StatusID]=?\n"
                    + " WHERE [OrderID]=" + oid;
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                //pre.setDataType(indexOf?,para);
                pre.setString(1, shippedDate);
                pre.setInt(2, statusID);
                n = pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String sql = "UPDATE [dbo].[Orders]\n"
                    + "   SET [ShippedDate]=?\n"
                    + "       ,[StatusID]=?\n"
                    + " WHERE [OrderID]=" + oid;
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                //pre.setDataType(indexOf?,para);
                pre.setString(1, shippedDate);
                pre.setInt(2, statusID);
                n = pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return n;

    }

    public int updateStatusFromUser(String shippedDate, int statusID, int oid) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [ShippedDate]=?\n"
                + "       ,[StatusID]=?\n"
                + " WHERE [OrderID]=" + oid;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?,para);
            pre.setString(1, shippedDate);
            pre.setInt(2, statusID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

//    public int deleteCategory(int id) {
//        int n = 0;
//        String sql = "delete from [Categories] where CategoryID=" + id;
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return n;
//    }
    public Vector<Order> getAllData(String sql) {
        Vector<Order> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                int userID = rs.getInt(2);
                String orderDate = rs.getString(3);
                String requiredDate = rs.getString(4);
                String shippedDate = rs.getString(5);
                double totalPrice = rs.getDouble(6);
                int statusID = rs.getInt(7);
                String address = rs.getString(8);
                String phone = rs.getString(9);
                String ReceiverName = rs.getString(10);
                Order cate = new Order(orderID, userID, orderDate, requiredDate, shippedDate, totalPrice, statusID, address, phone, ReceiverName);
                vector.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int getMaxID() {
        String sql = "SELECT MAX(OrderID)  FROM Orders";
        int n = 0;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
                return n;
            }
        } catch (SQLException e) {
        }
        return n;
    }

    public String getDate(String sql) {
        String orderDate = "";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                orderDate = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDate;
    }

    public Status getStatusByID(int staid) {
        String sql = "SELECT [StatusID]\n"
                + "      ,[StatusName]\n"
                + "  FROM [dbo].[Status]\n"
                + "  where [StatusID]=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, staid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Status satus = new Status(id, name);
                return satus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Order getOrderByOID(int Oid) {
        String sql = "SELECT [OrderID]\n"
                + "      ,[UserID]\n"
                + "      ,[OrderDate]\n"
                + "      ,[RequireDate]\n"
                + "      ,[ShippedDate]\n"
                + "      ,[TotalPrice]\n"
                + "      ,[StatusID]\n"
                + "      ,[DeliverAddress]\n"
                + "      ,[Phone]\n"
                + "      ,[ReceiverName]\n"
                + "  FROM [dbo].[Orders]\n"
                + "  where [OrderID]=" + Oid;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int OrderID = rs.getInt(1);
                int UserID = rs.getInt(2);
                String OrderDate = rs.getString(3);
                String RequireDate = rs.getString(4);
                String ShippedDate = rs.getString(5);
                double TotalPrice = rs.getDouble(6);
                int StatusID = rs.getInt(7);
                String DeliverAddress = rs.getString(8);
                String Phone = rs.getString(9);
                String ReceiverName = rs.getString(10);
                Order order = new Order(OrderID, UserID, OrderDate, RequireDate, ShippedDate, TotalPrice, StatusID, DeliverAddress, Phone, ReceiverName);
//                System.out.println(order);
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countOrder(String sql) {
        int n = 0;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
        Order order = dao.getOrderByOID(1);
        int n = dao.countOrder("select count(*) from Orders");
        System.out.println(n);
    }
}
