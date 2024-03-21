/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Role;
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
public class DAORole extends DBConnect {

    public int insertRoleByPrepared(Role role) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Role]\n"
                + "           ([RoleID]\n"
                + "           ,[RoleName])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, role.getRoleID());
            pre.setString(2, role.getRoleName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateRole(Role role) {
        int n = 0;
        String sql = "UPDATE [dbo].[Role]\n"
                + "   SET [RoleName] = ?\n"
                + " WHERE RoleID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?,para);
            pre.setString(1, role.getRoleName());
            pre.setInt(2, role.getRoleID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int deleteRolegory(int id) {
        int n = 0;
        String sql = "delete from [Role] where RoleID=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Role> getAllData(String sql) {
        Vector<Role> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
//              
                Role role = new Role(id, name);
                vector.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
