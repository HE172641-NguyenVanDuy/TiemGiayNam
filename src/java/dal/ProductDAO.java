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
import model.Brand;
import model.BrandSize;
import model.Categories;
import model.Product;
import model.Size;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    CategoryDAO categoryDAO = new CategoryDAO();

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                Brand b = getBrandByID(rs.getInt("brandID"));
                Categories c = categoryDAO.getCategoryByID(rs.getInt("categoryID"));
                p.setBrand(b);
                p.setCategory(c);
                p.setQuantity(rs.getInt("quantityInStock"));
                p.setQuantityOrder(rs.getInt("quantityOrder"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                Brand b = getBrandByID(rs.getInt("brandID"));
                Categories c = categoryDAO.getCategoryByID(rs.getInt("categoryID"));
                p.setBrand(b);
                p.setCategory(c);
                p.setQuantity(rs.getInt("quantityInStock"));
                p.setQuantityOrder(rs.getInt("quantityOrder"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductByBID(String bid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where brandID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                Brand b = getBrandByID(rs.getInt("brandID"));
                Categories c = categoryDAO.getCategoryByID(rs.getInt("categoryID"));
                p.setBrand(b);
                p.setCategory(c);
                p.setQuantity(rs.getInt("quantityInStock"));
                p.setQuantityOrder(rs.getInt("quantityOrder"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductBySearch(String searchName) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where ProductName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + searchName + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                Brand b = getBrandByID(rs.getInt("brandID"));
                Categories c = categoryDAO.getCategoryByID(rs.getInt("categoryID"));
                p.setBrand(b);
                p.setCategory(c);
                p.setQuantity(rs.getInt("quantityInStock"));
                p.setQuantityOrder(rs.getInt("quantityOrder"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductByID(String pid) {

        String sql = "select * from product where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                Brand b = getBrandByID(rs.getInt("brandID"));
                Categories c = categoryDAO.getCategoryByID(rs.getInt("categoryID"));
                p.setBrand(b);
                p.setCategory(c);
                p.setQuantity(rs.getInt("quantityInStock"));
                p.setQuantityOrder(rs.getInt("quantityOrder"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean deleteProductByID(int pid) {
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "WHERE productID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean insertProduct(String productName, String price, String brandID,
            String categoryID, String image, String quantity) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productName);
            stm.setString(2, price);
            stm.setString(3, image);
            stm.setString(4, brandID);
            stm.setString(5, categoryID);
            stm.setString(6, quantity);
            stm.setString(7, "0");
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public boolean updateProduct(String id, String productName, String price, String brandID,
            String categoryID, String image, String quantity) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[BrandID] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[quantityInStock] = ?\n"
                + "      ,[quantityOrder] = ?\n"
                + " WHERE productID = ?";
        try {
            ProductDAO dao = new ProductDAO();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productName);
            stm.setString(2, price);
            stm.setString(3, image);
            stm.setString(4, brandID);
            stm.setString(5, categoryID);
            stm.setString(6, quantity);
            stm.setInt(7, dao.getQuantityOrderByPID(id));
            stm.setString(8, id);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public int getQuantityOrderByPID(String id) {
        String sql = "select quantityOrder from product where productID = ?";
        int quantityOrder = 0;
        try {
            PreparedStatement stm  = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                quantityOrder  = rs.getInt("quantityOrder");
            }
        } catch (Exception e) {
            
        }
        return quantityOrder;
    }
    
    public List<Product> getProductBestSell() {
        String sql = "select top 10 * from product  order by quantityOrder desc";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement stm =connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                Brand b = getBrandByID(rs.getInt("brandID"));
                Categories c = categoryDAO.getCategoryByID(rs.getInt("categoryID"));
                p.setBrand(b);
                p.setCategory(c);
                p.setQuantity(rs.getInt("quantityInStock"));
                p.setQuantityOrder(rs.getInt("quantityOrder"));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public Brand getBrandByID(int bid) {
        String sql = "select * from Brand where BrandID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand c = new Brand(rs.getInt("BrandID"),
                        rs.getString("BrandName"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Size getSizeByID(int sid) {

        String sql = "select * from Size where SizeID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Size c = new Size(rs.getInt("SizeID"),
                        rs.getInt("name"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Brand getBrandNameByID(String bid) {

        String sql = "select * from Brand where BrandID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand c = new Brand(rs.getInt("brandID"),
                        rs.getString("brandName"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "select * from Brand";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Brand bs = new Brand(rs.getInt("BrandID"),
                        rs.getString("BrandName"));
                list.add(bs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BrandSize> getBrandSizeByBrandID(String cid) {
        List<BrandSize> list = new ArrayList<>();
        String sql = "select * from Brand_Size where brandID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                BrandSize bs = new BrandSize();
                Brand b = getBrandByID(rs.getInt("brandId"));
                Size s = getSizeByID(rs.getInt("sizeId"));
                bs.setBrand(b);
                bs.setSize(s);
                list.add(bs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertBrand(String brandName) {
        String sql = "Insert into Brand VALUES (?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, brandName);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    
}
