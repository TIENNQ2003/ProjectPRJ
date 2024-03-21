/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetail;
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
public class DAOOrderDetail extends DBConnect {

    public int insertOrderDetailByPrepared(OrderDetail orderDetail) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[OrderDetails]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[Quantity]\n"
                + "           ,[UnitPrice])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderDetail.getOrderID());
            pre.setInt(2, orderDetail.getProductID());
            pre.setInt(3, orderDetail.getQuantity());
            pre.setDouble(4, orderDetail.getUnitPrice());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

//    public int updateOrderDetail(Order order, int oid) {
//        int n = 0;
//        String sql = "UPDATE [dbo].[Orders]\n"
//                + "   SET [StatusID] =?\n"
//                + " WHERE [OrderID]=" + oid;
//        try {
//            PreparedStatement pre = conn.prepareStatement(sql);
//            //pre.setDataType(indexOf?,para);
//            pre.setInt(1, order.getStatusID());
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//
//    }
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
    public Vector<OrderDetail> getAllData(String sql) {
        Vector<OrderDetail> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                int proID = rs.getInt(2);
                int quantity = rs.getInt(3);
                double unitPrice = rs.getDouble(4);
                OrderDetail cate = new OrderDetail(orderID, proID, quantity, unitPrice);
                vector.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
