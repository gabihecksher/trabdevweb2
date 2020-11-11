/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
@WebServlet(urlPatterns = {"/ProcessaFormLogin"})
public class ProcessaFormLogin extends HttpServlet {
    Connection conection = null;
    
    public void init() throws ServletException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conection = DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", "");
            
            System.out.println("Conectei :D");
        } catch(ClassNotFoundException ex){
            System.out.println("Não foi possível encontrar o Driver!");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
            ex.printStackTrace();
        }
    }
    
    public void destroy() {
        try {
            conection.close();
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String legal_id = request.getParameter("legal_id");
            String password = request.getParameter("password");
            if (!legal_id.isEmpty() && !password.isEmpty()){
                response.sendRedirect("assets/templates/list_article.html");
//                request.getRequestDispatcher("/teste").forward(request,response);
            }
            else{
                PreparedStatement sql = conection.prepareStatement("insertinto contato values (?, ?)");
                sql.setString(2, "email@bla.com");
                sql.setString(3, "senhaaa");
                sql.setString(4, "nomeee");
                sql.setString(5, "cpf_teste");
                sql.setString(5, "papel_teste");
                sql.executeUpdate();
                System.out.println("criei o usuarioooo");

                request.getRequestDispatcher("/teste").forward(request,response);
//                response.sendRedirect("index.html");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
            ex.printStackTrace();
        } finally {
            out.close();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
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
