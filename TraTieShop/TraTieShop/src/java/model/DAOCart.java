/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
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
public class DAOCart extends DBConnect {

    public int insertCartByPrepared(Cart cart) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[cart]\n"
                + "           ([UserID]\n"
                + "           ,[ProductID]\n"
                + "           ,[Quantity]\n"
                + "           ,[dateInsert])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cart.getUserID());
            pre.setInt(2, cart.getProductID());
            pre.setInt(3, cart.getQuantity());
            pre.setString(4, cart.getDateInsert());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCart(Cart cart) {
        int n = 0;
        String sql = "UPDATE [dbo].[cart]\n"
                + "   SET [Quantity] = ?\n"
                + " WHERE [UserID] =? and [ProductID]=? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cart.getQuantity());
            pre.setInt(2, cart.getUserID());
            pre.setInt(3, cart.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int deleteCart(int Userid, int Productid) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[cart]\n"
                + "      WHERE [UserID] =" + Userid + " and [ProductID]=" + Productid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCartOverDate(Cart cart) {
        int n = 0;
        String dateNow = getDate("select getdate()");
        String sql = "select DATEDIFF(DAY,'" + cart.getDateInsert() + "','" + dateNow + "')";
        int datediff = 0;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                datediff = rs.getInt(1);
            }
            if (datediff >= 30) {
                n = deleteCart(cart.getUserID(), cart.getProductID());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Cart> getAllData(String sql) {
        Vector<Cart> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int UserID = rs.getInt(1);
                int proid = rs.getInt(2);
                int Quantity = rs.getInt(3);
                String dateInsert = rs.getString(4);
                Cart cart = new Cart(UserID, proid, Quantity, dateInsert);
                vector.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Cart getCart(int Userid, int Productid) {
        String sql = "select * from cart\n"
                + " WHERE [UserID] =? and [ProductID]=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, Userid);
            st.setInt(2, Productid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int Uid = rs.getInt(1);
                int pid = rs.getInt(2);
                int quantity = rs.getInt(3);
                String dateInsert = rs.getString(4);
                Cart cart = new Cart(Userid, Productid, quantity, dateInsert);
                return cart;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public static void main(String[] args) {
        DAOCart dao = new DAOCart();
        Cart cart = dao.getCart(2, 2);
       int n= dao.deleteCartOverDate(cart);
        if(n>0){
            System.out.println("delete sucess");
        }
    }
}
