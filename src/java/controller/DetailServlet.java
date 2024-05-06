/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Categories;
import model.Product;

/**
 *
 * @author Admin
 */
public class DetailServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //HttpSession session = request.getSession();
        String pID_raw = request.getParameter("pid");
        String bID_raw = request.getParameter("bid");
        String cID_raw = request.getParameter("cid");
        ProductDAO product = new ProductDAO();
        CategoryDAO category = new CategoryDAO(); 
        int cID = 0;
       
            try {
            if(cID_raw != null && bID_raw != null && cID_raw!= null) {
                cID = Integer.parseInt(cID_raw);
            } else {
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
         
                
        Product p = product.getProductByID(pID_raw);
        List<Categories> listCategory = category.getAllCategories();
        request.setAttribute("allBrand", product.getAllBrand());
        request.setAttribute("allCategory", listCategory);
        request.setAttribute("detail", p);
        request.setAttribute("sizeProduct", product.getBrandSizeByBrandID(bID_raw));
        request.setAttribute("name", product.getBrandNameByID(bID_raw));
        request.setAttribute("categoryProduct", category.getCategoryByID(cID));
        request.getRequestDispatcher("Views/Detail.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("Views/Detail.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
