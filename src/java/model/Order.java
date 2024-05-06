/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Order {
    
    private String orderID;
    private String account;
    private String totalMoney;
    private String orderDate;
    private int status;

    public Order() {
    }
    
    
    
    public Order(String orderID, String account, String totalMoney, String orderDate, int status) {
        this.orderID = orderID;
        this.account = account;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return account;
    }

    public void setUserName(String account) {
        this.account = account;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    
    

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", userName=" + account + ", totalMoney=" + totalMoney + ", orderDate=" + orderDate + '}';
    }
    
    
}
