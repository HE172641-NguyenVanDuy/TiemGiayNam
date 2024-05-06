/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.LocationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SignUpServlet extends HttpServlet {

    private String globalProvince;
    private String globalDistrict;
    private String globalWard;

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
            out.println("<title>Servlet SignUpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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
        LocationDAO locate = new LocationDAO();
        AccountDAO accountDAO = new AccountDAO();
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        globalWard = ward;
        globalProvince = province;
        globalDistrict = district;
        request.setAttribute("province", locate.getAllProvince());
        if (province != null) {
            request.setAttribute("district", locate.getDistrictByProvince(province));
            request.setAttribute("globalP", globalProvince);
        }
        if (district != null) {

            request.setAttribute("ward", locate.getWardByDistrict(district));
            request.setAttribute("globalD", globalDistrict);
            request.setAttribute("globalW", globalWard);
        }
        request.getRequestDispatcher("Views/Signup.jsp").forward(request, response);
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
        LocationDAO locate = new LocationDAO();
        AccountDAO accountDAO = new AccountDAO();
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String address = request.getParameter("address");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phonenumber");

        if (accountDAO.checkUserName(userName)) {

            globalWard = ward;
            globalProvince = province;
            globalDistrict = district;
            if (province != null) {
                request.setAttribute("district", locate.getDistrictByProvince(province));
                request.setAttribute("globalP", globalProvince);
            }
            if (district != null) {
                request.setAttribute("ward", locate.getWardByDistrict(district));
                request.setAttribute("globalD", globalDistrict);
                request.setAttribute("globalW", globalWard);
            }
            request.setAttribute("province", locate.getAllProvince());
            request.setAttribute("duplicate", "The User name already exist!");
            request.getRequestDispatcher("Views/Signup.jsp").forward(request, response);
        }
        else if(phoneNumber.length() != 10) {
            globalWard = ward;
            globalProvince = province;
            globalDistrict = district;
            if (province != null) {
                request.setAttribute("district", locate.getDistrictByProvince(province));
                request.setAttribute("globalP", globalProvince);
            }
            if (district != null) {
                request.setAttribute("ward", locate.getWardByDistrict(district));
                request.setAttribute("globalD", globalDistrict);
                request.setAttribute("globalW", globalWard);
            }
            request.setAttribute("province", locate.getAllProvince());
            request.setAttribute("wrong", "Wrong phone number!");
            request.getRequestDispatcher("Views/Signup.jsp").forward(request, response);
        }
        if (!accountDAO.checkUserName(userName) && !(phoneNumber.length() != 10)) {
            
                boolean acc = accountDAO.InsertIntoAccount(firstName, lastName, userName, password, province, district, ward, address, phoneNumber);

            response.sendRedirect("login");
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
