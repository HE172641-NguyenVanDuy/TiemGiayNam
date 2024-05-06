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
import model.Categories;



/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext{
    
    public static CategoryDAO instanceC = new CategoryDAO();
    
    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt("CategoryID"),
                        rs.getString("CategotyName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Categories getCategoryByID(int cid) {
        
        String sql = "select * from Categories where categoryID = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Categories c = new Categories(rs.getInt("CategoryID"),
                        rs.getString("CategotyName"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
        
    }
    
    public boolean insertCategory(String categoryName) {
        String sql = "Insert into Categories VALUES (?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, categoryName);   
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }
    
    
}
