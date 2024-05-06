/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class OrderDetail {
    private Order order;
    private Product product;
    private double price;
    private int size;
    private int quantity;
    private String deliveryAddress;
//    private String province;
//    private String district;
//    private String ward;
//    private String address;

    public OrderDetail() {
    }

    
    
    public OrderDetail(Order order, Product product, double price, int size, int quantity, String deliveryAddress) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "order=" + order + ", product=" + product + ", price=" + price + ", size=" + size + ", quantity=" + quantity + ", deliveryAddress=" + deliveryAddress + '}';
    }
    
    
}
