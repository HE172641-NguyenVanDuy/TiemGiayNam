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
import java.util.Map;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class StatisticDAO extends DBContext {

    private AccountDAO account = new AccountDAO();

    public int getTotalRevenue() {
        String sql = "select SUM(totalMoney) as totalRevenue from orders where status = 1 ";
        int totalRevenue = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getInt("totalRevenue");
            }
        } catch (Exception e) {
        }
        return totalRevenue;
    }

    public List<CategoryQuantity> getProductCategorySummaries() {
        List<CategoryQuantity> summaries = new ArrayList<>();
        String sql = "SELECT SUM(quantityOrder) as quantiByCategory, c.CategotyName FROM Product p JOIN Categories c ON p.CategoryID = c.CategoryID\n"
                + "GROUP BY c.CategotyName ORDER BY quantiByCategory DESC";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryQuantity cq = new CategoryQuantity();
                cq.setCategoryName(rs.getString("CategotyName"));
                cq.setQuantityOrder(rs.getInt("quantiByCategory"));
                summaries.add(cq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summaries;
    }

    public int getLocations(String province) {

        String sql = "SELECT  COUNT(*) as locations  FROM Account where province LIKE ?";
        int number = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + province + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("locations");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;

    }

    public int getQuantitySize(int size) {
        String sql = "select COUNT(*) as size from orderDetail where size = ?";
        int number = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, size);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public List<Integer> getSize() {
        List<Integer> list = new ArrayList<>();
        String sql = "select sizeID from size";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("sizeID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<String> getBrand() {
        List<String> list = new ArrayList<>();
        String sql = "select brandName from Brand";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("brandName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getQuantityBrand(String brand) {
        String sql = "  SELECT count(*) as brandName FROM  ORDERDETAIL OD JOIN PRODUCT P ON "
                + " P.PRODUCTID=OD.PRODUCTID JOIN  BRAND B "
                + "ON B.BRANDID = P.BRANDID WHERE B.BRANDNAME LIKE ?     ";
        int number = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + brand + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("brandName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public class CategoryQuantity {

        private String categoryName;
        private int quantityOrder;

        public CategoryQuantity() {
        }

        public CategoryQuantity(String categoryName, int quantityOrder) {
            this.categoryName = categoryName;
            this.quantityOrder = quantityOrder;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public int getQuantityOrder() {
            return quantityOrder;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setQuantityOrder(int quantityOrder) {
            this.quantityOrder = quantityOrder;
        }

    }

    
}
