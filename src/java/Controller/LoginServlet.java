/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aila-School
 */
public class LoginServlet extends HttpServlet {
    int counter = 0;
    Connection conn;
    public static String email, pass;
    public static String keystr, Cipherstr;
    public static String role = "none";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            keystr = config.getInitParameter("key");
            Cipherstr = config.getInitParameter("cipher");
            
            
            Class.forName(config.getInitParameter("jdbcClassName"));
            String username = config.getInitParameter("dbUserName");
            String password = config.getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("databaseName"));
            conn
                    = DriverManager.getConnection(url.toString(), username, password);
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        }
        
        System.out.println("done!");
        
    }
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
            email = request.getParameter("email");
            pass = request.getParameter("password");
            
            System.out.println("continue!");
            if (conn != null) {
                String query = "SELECT * FROM USERS WHERE email = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, email);

                 // ResultSet rs = stmt.executeQuery(query);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {  
                String pword = (Security.decrypt(rs.getString("password").trim()));
        
                
                    if (email.equals(rs.getString("email").trim()) && pass.equals(pword)) {
                        if(rs.getString("UserRole").trim().equals("admin")){
                            request.getRequestDispatcher("adminPage.jsp").forward(request, response);    
                        } else {            
                            request.getRequestDispatcher("guestPage.jsp").forward(request, response);    

                        }
                        

                    } else {
                        counter++;

                        request.setAttribute("limit", counter);
                        request.getRequestDispatcher("incorrect.jsp").forward(request, response);

                    }

                } else {
                    counter++;
                    request.setAttribute("limit", counter);
                    request.getRequestDispatcher("incorrect.jsp").forward(request, response);
                }
                // Close the connection
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException sqle) {
            response.sendRedirect("error.jsp");
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
        processRequest(request, response);
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
