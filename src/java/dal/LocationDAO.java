/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.District;
import model.Province;
import model.Ward;

/**
 *
 * @author Admin
 */
public class LocationDAO extends DBContext{
    
    public List<Province> getAllProvince() {
        List<Province> list = new ArrayList<>();
        String sql = "select DISTINCT province from Locations ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Province l = new Province();
                l.setProvince(rs.getString("province"));
                
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    } 
    
    public List<District> getDistrictByProvince(String province) {
        List<District> list = new ArrayList<>();
        String sql = "select DISTINCT district from Locations where province LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, province);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                District d = new District();
                d.setDistrict(rs.getString("district"));
                
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Ward> getWardByDistrict(String district) {
        List<Ward> list = new ArrayList<>();
        String sql = "select DISTINCT Ward from Locations where district = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, district);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ward d = new Ward();
                d.setWard(rs.getString("ward"));
                
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<String> getProvince() {
        String sql = "select DISTINCT province from Locations ";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("province"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
