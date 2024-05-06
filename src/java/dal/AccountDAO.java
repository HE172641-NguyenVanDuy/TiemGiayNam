/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public List<Account> getAllAccountUser() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from account where roleid = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "2");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setFirstName(rs.getString("firstname"));
                a.setLastName(rs.getString("lastname"));
                a.setUserName(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setProvince(rs.getString("province"));
                a.setDistrict(rs.getString("district"));
                a.setWard(rs.getString("ward"));
                a.setAddress(rs.getString("address"));
                Role r = getRoleByRoleID(rs.getInt("roleID"));
                a.setRole(r);
                a.setPhoneNumber(rs.getString("phonenumber"));
                list.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void updateRole(String userName, int roleID) {
        String sql = "UPDATE Account SET roleID = ? where username = ? ";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleID);
            stm.setString(2, userName);
            stm.executeUpdate();
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Role> getAllRole() {
        List<Role> list = new ArrayList<>();
        String sql = "SELECT * FROM Role";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRole(rs.getString(2));
                role.setRoleID(rs.getInt(1));
                list.add(role);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean InsertIntoAccount(String fName, String lName, String userName,
            String password, String province, String district, String ward, String address,
            String phoneNumber) {
        String sql = "Insert into Account Values(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, fName);
            stm.setString(2, lName);
            stm.setString(3, userName);
            stm.setString(4, password);
            stm.setString(5, province);
            stm.setString(6, district);
            stm.setString(7, ward);
            stm.setString(8, address);
            stm.setString(9, phoneNumber);
            stm.setInt(10, 2);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public boolean checkUserName(String username) {
        String sql = "select * from account where username = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Role getRoleByRoleID(int role) {
        String sql = "select * from role where roleID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Role c = new Role(rs.getInt("roleID"),
                        rs.getString("roleName"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account checkAccountLogin(String userName, String password) {
        String sql = "select * from Account where userName = ? AND password = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setFirstName(rs.getString("firstname"));
                a.setLastName(rs.getString("lastname"));
                a.setUserName(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setProvince(rs.getString("province"));
                a.setDistrict(rs.getString("district"));
                a.setWard(rs.getString("ward"));
                a.setAddress(rs.getString("address"));
                Role r = getRoleByRoleID(rs.getInt("roleID"));
                a.setRole(r);
                a.setPhoneNumber(rs.getString("phonenumber"));
                return a;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
}
