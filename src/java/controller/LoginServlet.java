/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        HttpSession session = request.getSession();

        Cookie uCookie = new Cookie("uCookie", userName);
        Cookie pCookie = new Cookie("pCookie", password);
        Cookie rCookie = new Cookie("rCookie", remember);

        if (remember != null) {
            uCookie.setMaxAge(60 * 60 * 24);
            pCookie.setMaxAge(60 * 60 * 24);
            rCookie.setMaxAge(60 * 60 * 24);
        } else {
            uCookie.setMaxAge(0);
            pCookie.setMaxAge(0);
            rCookie.setMaxAge(0);
        }
        // lưu vào trong browser
        response.addCookie(uCookie);
        response.addCookie(pCookie);
        response.addCookie(rCookie);

        AccountDAO account = new AccountDAO();
        Account acc = account.checkAccountLogin(userName, password);

        if (acc == null) {
            session.invalidate();
            request.setAttribute("notice", "The username does not existed.");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
        } else {
            session.setAttribute("acc", acc);
            if (acc.getRole().getRoleID() == 1) {
                response.sendRedirect("home");
            } else if (acc.getRole().getRoleID() == 2) {
                Cookie[] arr = request.getCookies();
                String txt = "";
                if (arr != null) {
                    for (Cookie i : arr) {
                        session.setAttribute("username", userName);
                        if (i.getName().equals("Cart")) {
                            txt += i.getValue();
                            i.setMaxAge(0);
                            response.addCookie(i);
                        }
                    }
                }

                Cookie c = new Cookie("Cart", txt);
                c.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(c);

                response.sendRedirect("home");
            }

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
