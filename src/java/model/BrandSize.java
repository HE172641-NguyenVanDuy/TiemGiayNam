/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class BrandSize {
    private Brand brand;
    private Size size;

    public BrandSize() {
    }

    public BrandSize(Brand brand, Size size) {
        this.brand = brand;
        this.size = size;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BrandSize{" + "brand=" + brand + ", size=" + size + '}';
    }

    

    

    
    
}
