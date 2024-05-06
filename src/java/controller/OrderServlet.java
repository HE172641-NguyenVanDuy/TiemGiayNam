/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class OrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO product = new ProductDAO();
        OrderDAO order = new OrderDAO();
        List<Product> listProduct = product.getAllProduct();

        Cookie[] arr = request.getCookies();
        HttpSession session = request.getSession();
        LocalDateTime localtime = LocalDateTime.now();
        String orderID = localtime.getYear() + "" + localtime.getMonthValue() + ""
                + localtime.getDayOfMonth() + "" + localtime.getHour() + ""
                + localtime.getMinute() + "" + localtime.getSecond();
        Account acc = (Account) session.getAttribute("acc");
        String userName = (String) session.getAttribute("username");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String orderDate = currentDate.format(formatter);

        String txt = "";
        if (arr != null) {
            for (Cookie i : arr) {
                if (i.getName().equals("Cart")) {
                    txt += i.getValue();
                    //if (i.getValue().contains(userName)) {
                    i.setMaxAge(0);
                    response.addCookie(i);
                    //}
                }
            }
        }
        Cart cart = new Cart(txt, listProduct, userName);
        
        if (acc != null) {
            String address = acc.getProvince() + "," + acc.getDistrict() + "," + acc.getWard() + "," + acc.getAddress();
            order.insertOrderToPendingList(orderID, acc, cart, orderDate, 0, address);
            List<Item> list = (List<Item>) session.getAttribute("listItem");
            for (Item item : list) {
                cart.removeItems(item.getProduct().getProductID(), item.getSize());
            }
            

            String text1 = "";
            String[] TemptItem = txt.split("'");

            if (!TemptItem[0].contains(userName)) {
                text1 += TemptItem[0];
            }
            for (int i = 1; i < TemptItem.length; i++) {
                if (!TemptItem[i].contains(userName)) {
                    text1 += "'" + TemptItem[i];
                }
            }

            List<Item> items = cart.getItems();
            txt = "";
            if (items.size() > 0) {
                if (text1.isEmpty()) {
                    text1 = items.get(0).getUserName() + ":" + items.get(0).getProduct().getProductID() + ":"
                            + items.get(0).getQuantity() + ":" + items.get(0).getSize();
                    for (int i = 1; i < items.size(); i++) {
                        text1 += "'" + items.get(0).getUserName() + ":" + items.get(i).getProduct().getProductID() + ":"
                                + items.get(i).getQuantity() + ":" + items.get(i).getSize();
                    }
                } else {
                    for (int i = 0; i < items.size(); i++) {
                        text1 += "'" + items.get(0).getUserName() + ":" + items.get(i).getProduct().getProductID() + ":"
                                + items.get(i).getQuantity() + ":" + items.get(i).getSize();
                    }
                }

            }
            Cookie c = new Cookie("Cart", text1);
            
            c.setMaxAge(7 * 60 * 60 * 24);
            response.addCookie(c);
            response.sendRedirect("show");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        ProductDAO product = new ProductDAO();
//        List<Product> listProduct = product.getAllProduct();
//        Cookie[] arr = request.getCookies();
//        List<Item> listI = new ArrayList<>();
//        HttpSession session = request.getSession();
//        //HttpSession session = request.getSession();
//        String txt = "";
//        if (arr != null) {
//            for (Cookie i : arr) {
//                if (i.getName().equals("Cart")) {
//                    txt += i.getValue();
//                }
//            }
//        }
//
//        String userName = (String) session.getAttribute("username");
//        Cart cart = new Cart(txt, listProduct, userName);
//        request.setAttribute("list", cart.getItems());
//response.sendRedirect("show");
       request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
