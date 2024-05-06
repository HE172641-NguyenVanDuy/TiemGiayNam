/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.LocationDAO;
import dal.StatisticDAO;
import dal.StatisticDAO.CategoryQuantity;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Categories;

/**
 *
 * @author Admin
 */
public class StatisticServlet extends HttpServlet {

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
            out.println("<title>Servlet StatisticServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticServlet at " + request.getContextPath() + "</h1>");
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
        StatisticDAO statistic = new StatisticDAO();
        CategoryDAO cate = new CategoryDAO();
        LocationDAO location = new LocationDAO();
        List<CategoryQuantity> list = statistic.getProductCategorySummaries();
        
        List<String> listLocation = location.getProvince();
        List<Integer> listSize = statistic.getSize();
        Map<String,Integer> quantityLocate = new HashMap<>();
        for (String i : listLocation) {
            int number = statistic.getLocations(i);
            quantityLocate.put(i, number);
        }
        
        Map<Integer,Integer> quantitySize = new HashMap<>();
        for (Integer i : listSize) {
            int number = statistic.getQuantitySize(i);
            quantitySize.put(i, number);
        }
        
        Map<String,Integer> quantityBrand = new HashMap<>();
        for (String i : statistic.getBrand()) {
            int number = statistic.getQuantityBrand(i);
            quantityBrand.put(i, number);
        }
        
        request.setAttribute("quantityBrand", quantityBrand);
        request.setAttribute("quantitySize", quantitySize);
        request.setAttribute("quantityLocate", quantityLocate);
        request.setAttribute("categoryQuan", list);
        request.setAttribute("totalRevenue", statistic.getTotalRevenue());
        request.getRequestDispatcher("Views/Statistic.jsp").forward(request, response);
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
        request.getRequestDispatcher("Views/Statistic.jsp").forward(request, response);
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
