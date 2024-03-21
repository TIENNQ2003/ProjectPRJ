/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
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
public class DAOProduct extends DBConnect {

    public int insertProductByPrepared(Product product) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductId]\n"
                + "           ,[ProductName]\n"
                + "           ,[CategoryID]\n"
                + "           ,[Description]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitinStock]\n"
                + "           ,[Discount]\n"
                + "           ,[status]\n"
                + "           ,[Image])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        //number of ? = number fields
        //index ? start  1
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?,para);
            pre.setInt(1, product.getProductId());
            pre.setString(2, product.getProductName());
            pre.setInt(3, product.getCategoryID());
            pre.setString(4, product.getDescription());
            pre.setDouble(5, product.getUnitPrice());
            pre.setInt(6, product.getUnitinStock());
            pre.setDouble(7, product.getDiscount());
            pre.setInt(8, product.getStatus());
            pre.setString(9, product.getImage());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product product) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[UnitinStock] = ?\n"
                + "      ,[Discount] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[Image] = ?\n"
                + " WHERE ProductId=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getCategoryID());
            pre.setString(3, product.getDescription());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getUnitinStock());
            pre.setDouble(6, product.getDiscount());
            pre.setInt(7, product.getStatus());
            pre.setString(8, product.getImage());
            pre.setInt(9, product.getProductId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int deleteProduct(int productid) {
        int n = 0;
        String sql = "delete Products where ProductId=" + productid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Product> getAllProduct(String sql) {
        Vector<Product> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int cateid = rs.getInt(3);
                String descr = rs.getString(4);
                double price = rs.getDouble(5);
                int unitIn = rs.getInt(6);
                double discount = rs.getDouble(7);
                int status = rs.getInt(8);
                String images = rs.getString(9);
                Product pro = new Product(id, name, cateid, descr, price, unitIn, discount, status, images);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Product getProductByID(int id) {
        String sql = "select * from Products where ProductID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int proid = rs.getInt(1);
                String name = rs.getString(2);
                int cateid = rs.getInt(3);
                String descr = rs.getString(4);
                double price = rs.getDouble(5);
                int unitIn = rs.getInt(6);
                double discount = rs.getDouble(7);
                int status = rs.getInt(8);
                String images = rs.getString(9);
                Product pro = new Product(proid, name, cateid, descr, price, unitIn, discount, status, images);
                return pro;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        Vector<Product> pro = dao.getAllProduct("select top(4) * from Products where CategoryID='" + 1 + "' order by UnitPrice desc");
        System.out.println(pro);
        
    }
}
