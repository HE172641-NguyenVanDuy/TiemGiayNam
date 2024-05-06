/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProcessServlet extends HttpServlet {

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
            out.println("<title>Servlet ProcessServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        List<Product> listProduct = product.getAllProduct();
        Cookie[] arr = request.getCookies();

        String txt = "";
        if (arr != null) {
            for (Cookie i : arr) {
                if (i.getName().equals("Cart")) {
                    txt += i.getValue();
                    i.setMaxAge(0);
                    response.addCookie(i);
                }
            }
        }
        String userName = (String) session.getAttribute("username");
        Cart cart = new Cart(txt, listProduct, userName);

        String num_raw = request.getParameter("num");
        String id_raw = request.getParameter("id");
        String size_raw = request.getParameter("size");
        int id;
        int num = 0;
        int size;
        try {
            id = Integer.parseInt(id_raw);
            num = Integer.parseInt(num_raw);
            size = Integer.parseInt(size_raw);
            Product p = product.getProductByID(id_raw);
            int numStore = p.getQuantity();
            if ((num == -1) && (cart.getQuantityByID(id, size) <= 1)) {
                cart.removeItems(id, size);
            } else {
                if ((num == 1) && (cart.getQuantityByID(id, size) >= numStore)) {
                    num = 0;
                }
                Item i = new Item(userName, p, num, p.getPrice(), size);
                cart.addItem(i);
            }
        } catch (Exception e) {
        }

        List<Item> items = cart.getItems();
        if (items.size() > 0) {
            txt = items.get(0).getUserName() + ":" + items.get(0).getProduct().getProductID() + ":"
                    + items.get(0).getQuantity() + ":" + items.get(0).getSize();
            for (int i = 1; i < items.size(); i++) {
                txt += "'" + items.get(0).getUserName() + ":" + items.get(i).getProduct().getProductID() + ":"
                        + items.get(i).getQuantity() + ":" + items.get(i).getSize();
            }
        }

        Cookie c = new Cookie("Cart", txt);
        c.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(c);

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
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
        ProductDAO product = new ProductDAO();
        HttpSession session = request.getSession();
        List<Product> listProduct = product.getAllProduct();
        Cookie[] arr = request.getCookies();

        String txt = "";
        if (arr != null) {
            for (Cookie i : arr) {
                if (i.getName().equals("Cart")) {
                    txt += i.getValue();
                    i.setMaxAge(0);
                    response.addCookie(i);
                }
            }
        }
        
        String userName = (String) session.getAttribute("username");
        String id = request.getParameter("id");
        String size = request.getParameter("size");
       
        String[] ids = txt.split("'");
        String out = "";
        for (int i = 0; i < ids.length; i++) {
            String[] s = ids[i].split(":");
            if ( !s[0].equals(userName) || !s[1].equals(id) || !s[3].equals(size) ) { 
                if (out.isEmpty()) {
                    out = ids[i];
                } else {
                    out += "'" + ids[i];
                }
            }
        }
        
        if (!out.isEmpty()) {
            Cookie c = new Cookie("Cart", out);
            c.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(c);
        }
        
        
        Cart cart = new Cart(out, listProduct, userName);
        request.setAttribute("cart", cart);
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
