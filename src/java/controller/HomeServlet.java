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
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Cart;
import model.Categories;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO category = new CategoryDAO();
        ProductDAO product = new ProductDAO();
        HttpSession session = request.getSession();
        List<Product> listProduct = product.getAllProduct();
        List<Categories> listCategory = category.getAllCategories();
        List<Brand> listBrand = product.getAllBrand();
                        
        Cookie[] arr = request.getCookies();
        
        String txt = "";
        String userName = (String) session.getAttribute("username");
        Cart cart = new Cart(txt, listProduct, userName);
        
          
        int page, numberpage = 12;
        int size = listProduct.size();
        String xPage = request.getParameter("page");
        if(xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }
        int start,end;
        int num = (size%12==0?(size/12):((size/12)+1));
        start = (page-1)*numberpage;
        end = Math.min(page*numberpage, size) ;
        List<Product> listPagination = product.getListByPage(listProduct, start, end);
        
        if (arr != null) {
            for (Cookie i : arr) {
                if (i.getName().equals("Cart")) {
                    txt += i.getValue();
                }
            }
        }

        request.setAttribute("page", page);
        request.setAttribute("number", num);
        request.setAttribute("allBrand", listBrand);
        request.setAttribute("allProduct", listPagination);

        request.setAttribute("allCategory", listCategory);
        request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
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
        request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
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
