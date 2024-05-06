/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.*;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {
    
    ProductDAO product = new ProductDAO();
    
    public void insertOrderToPendingList(String order, Account acc, Cart cart, String orderDate, int status, String address) {
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, order);
            stm.setString(2, acc.getUserName());
            stm.setString(3, cart.getTotalMoney());
            stm.setString(4, orderDate);
            stm.setInt(5, status);
            stm.executeUpdate();
            String sql1 = "Select orderID from orders  where orderID = ? ";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, order);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                //order1 la orderID cua orderDetail
                String order1 = rs.getString("orderID");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into orderdetail values(?,?,?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setString(2, order1);
                    st2.setInt(1, i.getProduct().getProductID());
                    st2.setDouble(3, i.getPrice());
                    st2.setInt(4, i.getSize());
                    st2.setInt(5, i.getQuantity());
                    st2.setString(6, address);
                    st2.executeUpdate();
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertOrder(String order, Account acc, Cart cart, String orderDate, int status, String address) {
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, order);
            stm.setString(2, acc.getUserName());
            stm.setString(3, cart.getTotalMoney());
            stm.setString(4, orderDate);
            stm.setInt(5, status);
            stm.executeUpdate();
            String sql1 = "Select orderID from orders  where orderID = ? ";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, order);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                //order1 la orderID cua orderDetail
                String order1 = rs.getString("orderID");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into orderdetail values(?,?,?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setString(2, order1);
                    st2.setInt(1, i.getProduct().getProductID());
                    st2.setDouble(3, i.getPrice());
                    st2.setInt(4, i.getSize());
                    st2.setInt(5, i.getQuantity());
                    st2.setString(6, address);
                    st2.executeUpdate();
                }
            }

            // cap nha lai so luong san pham
            String sql3 = "update product set quantityInStock = quantityInStock - ?, quantityOrder = quantityOrder + ? where productID = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getQuantity());
                st3.setInt(3, i.getProduct().getProductID());
                st3.executeUpdate();
                // return st3.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // return false;
    }
    
    public void updateQuantityInStock(String orderID) {
        
        try {            
            String sql = "SELECT od.productID, quantity, quantityInStock, quantityOrder "
                    + "FROM orderdetail od JOIN product p ON od.productID = p.productID"
                    + " WHERE od.orderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
//                int quantityInStock = rs.getInt("quantityInStock");
//                int quantityOrder = rs.getInt("quantityOrder");
                String sql2 = "UPDATE product SET quantityInStock = quantityInStock - ?, quantityOrder = quantityOrder + ? WHERE productID = ?";
                PreparedStatement stm2=  connection.prepareStatement(sql2);
                stm2.setInt(1, quantity);
                stm2.setInt(2, quantity);
                stm2.setInt(3, productID);
                stm2.executeUpdate();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateOrderToSuccess(String orderID) {
        String sql = "UPDATE orders set status =  1 where orderID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.executeQuery();
            
        } catch (Exception e) {
        }
    }
    
    public void updateQuantityInStockToPending(String orderID) {
        
        try {            
            String sql = "SELECT od.productID, quantity, quantityInStock, quantityOrder "
                    + "FROM orderdetail od JOIN product p ON od.productID = p.productID"
                    + " WHERE od.orderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
//                int quantityInStock = rs.getInt("quantityInStock");
//                int quantityOrder = rs.getInt("quantityOrder");
                String sql2 = "UPDATE product SET quantityInStock = quantityInStock + ?, quantityOrder = quantityOrder - ? WHERE productID = ?";
                PreparedStatement stm2=  connection.prepareStatement(sql2);
                stm2.setInt(1, quantity);
                stm2.setInt(2, quantity);
                stm2.setInt(3, productID);
                stm2.executeUpdate();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateOrderToPending(String orderID) {
        String sql = "UPDATE orders set status = 0 where orderID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.executeQuery();
            
        } catch (Exception e) {
        }
    }
    
    
    public List<Order> getAllPedingList() {
        List<Order> list = new ArrayList<>();
        
        String sql = "SELECT * FROM orders where status = 0";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getString(1));
                order.setUserName(rs.getString(2));
                order.setTotalMoney(rs.getString(3));
                order.setOrderDate(rs.getString(4));
                order.setStatus(rs.getInt(5));
                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return list;
    }
    
    public void deleteOrder(String orderid) {
        
        try {
            String sql = "DELETE FROM Orderdetail WHERE orderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderid);
            
            stm.executeUpdate();
            String sql2 = "DELETE FROM Orders WHERE orderID = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm.setString(1, orderid);
            stm2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        
        String sql = "SELECT * FROM orders where status = 1";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getString(1));
                order.setUserName(rs.getString(2));
                order.setTotalMoney(rs.getString(3));
                order.setOrderDate(rs.getString(4));
                
                order.setStatus(rs.getInt(5));
                
                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return list;
    }
    
    public Order getOrderByOrderID(String orderID) {
        String sql = "SELECT * FROM ORders where orderID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Order o = new Order();
                
                o.setOrderID(rs.getString(1));
                o.setUserName(rs.getString(2));
                o.setTotalMoney(rs.getString(3));
                o.setOrderDate(rs.getString(4));
                o.setStatus(rs.getInt(5));
                return o;
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<OrderDetail> getAllOrderDetailByOrderID(String orderID) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM orderDetail WHERE orderId = ? ";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = product.getProductByID(rs.getString(1));
                Order o = getOrderByOrderID(rs.getString(2));
                
                OrderDetail od = new OrderDetail();
                od.setProduct(p);
                od.setOrder(o);
                od.setPrice(rs.getDouble(3));
                od.setSize(rs.getInt(4));
                od.setQuantity(rs.getInt(5));
                od.setDeliveryAddress(rs.getString(6));
                list.add(od);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }


    
}
