/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.User;
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
public class DAOUser extends DBConnect {

    public int insertUserByPrepared(User user) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([UserID]\n"
                + "           ,[FullName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[Gender]\n"
                + "           ,[Email]\n"
                + "           ,[UserName]\n"
                + "           ,[PassWord]\n"
                + "           ,[RoleID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        //number of ? = number fields
        //index ? start  1
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?,para);
            pre.setInt(1, user.getUserID());
            pre.setString(2, user.getFullName());
            pre.setString(3, user.getAddress());
            pre.setString(4, user.getPhone());
            pre.setInt(5, user.getGender());
            pre.setString(6, user.getEmail());
            pre.setString(7, user.getUserName());
            pre.setString(8, user.getPassWord());
            pre.setInt(9, user.getRoleID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProfile(User user) {
        int n = 0;
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [FullName] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[UserName] = ?\n"
                + "      ,[PassWord] = ?\n"
                + "      ,[RoleID] = ?\n"
                + " WHERE UserID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getFullName());
            pre.setString(2, user.getAddress());
            pre.setString(3, user.getPhone());
            pre.setDouble(4, user.getGender());
            pre.setString(5, user.getEmail());
            pre.setString(6, user.getUserName());
            pre.setString(7, user.getPassWord());
            pre.setInt(8, user.getRoleID());
            pre.setInt(9, user.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int changePassWord(int UserID, String newPass) {
        int n = 0;
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET[PassWord] = ?\n"
                + " WHERE UserID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, newPass);
            pre.setInt(2, UserID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int deleteUser(int UserID) {
        int n = 0;
        String sql = "delete from [User] where UserID=" + UserID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<User> getAllUser(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String Address = rs.getString(3);
                String Phone = rs.getString(4);
                int Gender = rs.getInt(5);
                String email = rs.getString(6);
                String UserName = rs.getString(7);
                String PassWord = rs.getString(8);
                int RoleID = rs.getInt(9);
                User user = new User(id, name, Address, Phone, Gender, email, UserName, PassWord, RoleID);
                vector.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public User checkRegister(String email, String username) {
        String sql = "SELECT [UserID]\n"
                + "      ,[FullName]\n"
                + "      ,[Address]\n"
                + "      ,[Phone]\n"
                + "      ,[Gender]\n"
                + "      ,[Email]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[RoleID]\n"
                + "  FROM [dbo].[User]\n"
                + "  where [Email]=? or [UserName]=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User a = new User(rs.getInt("UserID"), rs.getString("FullName"), rs.getString("Address"),
                        rs.getString("Phone"), rs.getInt("Gender"), rs.getString("Email"),
                        rs.getString("UserName"), rs.getString("PassWord"), rs.getInt("RoleID"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User checkLogin(String username, String password) {
        String sql = "SELECT [UserID]\n"
                + "      ,[FullName]\n"
                + "      ,[Address]\n"
                + "      ,[Phone]\n"
                + "      ,[Gender]\n"
                + "      ,[Email]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[RoleID]\n"
                + "  FROM [dbo].[User]\n"
                + "  where [UserName]=? and [PassWord]=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User a = new User(rs.getInt("UserID"), rs.getString("FullName"), rs.getString("Address"),
                        rs.getString("Phone"), rs.getInt("Gender"), rs.getString("Email"),
                        rs.getString("UserName"), rs.getString("PassWord"), rs.getInt("RoleID"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getMaxID() {
        String sql = "SELECT MAX(UserID)\n"
                + "  FROM [dbo].[User]";
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

    public User getUserByID(int id) {
        String sql = "SELECT [UserID]\n"
                + "      ,[FullName]\n"
                + "      ,[Address]\n"
                + "      ,[Phone]\n"
                + "      ,[Gender]\n"
                + "      ,[Email]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[RoleID]\n"
                + "  FROM [dbo].[User]\n"
                + "  where [UserID]=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int UserID = rs.getInt(1);
                String FullName = rs.getString(2);
                String Address = rs.getString(3);
                String Phone = rs.getString(4);
                int Gender = rs.getInt(5);
                String Email = rs.getString(6);
                String UserName = rs.getString(7);
                String PassWord = rs.getString(8);
                int RoleID = rs.getInt(9);
                User pro = new User(UserID, FullName, Address, Phone, Gender, Email, UserName, PassWord, RoleID);
                return pro;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        Vector<User> users = dao.getAllUser("select * from [User]");
        System.out.println(users);
    }
}
