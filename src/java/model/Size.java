/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Size {
    private int sizeID;
    private int name;

    public Size() {
    }

    public Size(int sizeID, int name) {
        this.sizeID = sizeID;
        this.name = name;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Size{" + "sizeID=" + sizeID + ", name=" + name + '}';
    }

    
    
    
}
