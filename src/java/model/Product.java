

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    private int productID;
    private String productName;
    private double price;
    private String image;
    private Brand  brand;
    private Categories  category;
    private int  quantity;
    private int  quantityOrder;
    
    public Product() {
    }

    public Product(int productID, String productName, double price, String image, Brand brand, Categories category, int quantity, int quantityOrder) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.quantityOrder =quantityOrder;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }
    
    

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", image=" + image + ", brand=" + brand + ", category=" + category + ", quantity=" + quantity + '}';
    }

    
    
}
