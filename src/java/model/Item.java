/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Item {
    
    private String userName;
    private Product product;
    private int quantity;
    private double price;
    private int size;
//    private BrandSize size;
//    private Brand brand;

    public Item() {
    }

    
    public Item(String userName, Product product, int quantity, double price, int size) {
        this.userName = userName;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
    }   

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    
//    public BrandSize getBrandSize() {
//        return size;
//    }
//
//    public void setBrandSize(BrandSize size) {
//        this.size = size;
//    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + ", price=" + price + ", size=" + size + '}';
    }
    
    
    
}
