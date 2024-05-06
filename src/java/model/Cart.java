/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String txt, List<Product> listP, String userName) {
        items = new ArrayList<>();
        try {

            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("'");
                for (String i : s) {
                    String[] n = i.split(":");
                    String user = n[0];
                    int pid = Integer.parseInt(n[1]);
                    int quantity = Integer.parseInt(n[2]);
                    int size = Integer.parseInt(n[3]);
                    Product p = getProductByID(pid, listP);
                    if (user.equals(userName)) {
                        Item item = new Item(user, p, quantity, p.getPrice(), size);
                        addItem(item);
                    }

                }
            }
        } catch (Exception e) {
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityByID(int pid, int size) {
        return getItemByID(pid, size).getQuantity();
    }

    private Item getItemByID(int pid, int size) {
        for (Item i : items) {
            if (i.getProduct().getProductID() == pid && i.getSize() == size) {
                return i;
            }
        }
        return null;
    }

    public void addItem(Item i) {
        // sản phẩm đã có trong giỏ hàng
        if (getItemByID(i.getProduct().getProductID(), i.getSize()) != null) {
            Item m = getItemByID(i.getProduct().getProductID(), i.getSize());
            m.setQuantity(m.getQuantity() + i.getQuantity());
        } else {
            // chưa có trong giỏ ahngf
            items.add(i);
        }
    }

    public void removeItems(int pid, int size) {
        if (getItemByID(pid, size) != null) {
            items.remove(getItemByID(pid, size));
        }

    }

    public String getTotalMoney() {
        double t = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#");
        
        for (Item i : items) {
            t += (i.getQuantity() * i.getPrice());

        }
        String money = decimalFormat.format(t);
        return money;
    }

    private Product getProductByID(int pid, List<Product> list) {
        for (Product i : list) {
            if (i.getProductID() == pid) {
                return i;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        
        List<Item> list = new ArrayList<>();
        System.out.println(list.size());
    }

}
