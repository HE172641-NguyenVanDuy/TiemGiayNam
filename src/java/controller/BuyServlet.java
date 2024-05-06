/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
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
import model.Categories;
import model.Item;

import model.Product;

/**
 *
 * @author Admin
 */
public class BuyServlet extends HttpServlet {
    
    
    //private int quantityCheck;
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
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        ProductDAO product = new ProductDAO();
        List<Product> listProduct = product.getAllProduct();
        CategoryDAO category = new CategoryDAO();
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
        String pid = request.getParameter("pid");
        String quantity_raw = request.getParameter("quantity");
        String size_raw = request.getParameter("size");
        int id = 0;
        int size = 0;
        int quantity = 0;
        try {
            id = Integer.parseInt(pid);
            size = Integer.parseInt(size_raw);
            quantity = Integer.parseInt(quantity_raw);
            Product p = product.getProductByID(pid);
            int quantityInStock = p.getQuantity();
            
             
            if (quantityInStock >= quantity) {
           
            
                //if (quantityCheck > 0) {
                    if (txt != null) {
                        if (txt.isEmpty()) {
                            txt += userName + ":" + pid + ":" + quantity + ":" + size;
                        } else {
                            txt += "'" + userName + ":" + pid + ":" + quantity + ":" + size;
                        }
                    }
                    //quantityCheck = quantityInStock - quantity;
                    Cookie c = new Cookie("Cart", txt);
                    c.setMaxAge(7 * 24 * 60 * 60);
                    response.addCookie(c);

                    response.sendRedirect("show");
                //} 

            } else {

                List<Categories> listCategory = category.getAllCategories();
                request.setAttribute("allBrand", product.getAllBrand());
                request.setAttribute("allCategory", listCategory);
                request.setAttribute("detail", p);
                request.setAttribute("sizeProduct", product.getBrandSizeByBrandID(p.getBrand().getBrandID() + ""));
                request.setAttribute("name", product.getBrandNameByID(p.getBrand().getBrandID() + ""));
                request.setAttribute("categoryProduct", category.getCategoryByID(p.getCategory().getCategoryID()));
                request.setAttribute("notice", "Out of stock !");
                //session.setAttribute("notice", "Out of stock !");
                request.getRequestDispatcher("Views/Detail.jsp").forward(request, response);

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

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
