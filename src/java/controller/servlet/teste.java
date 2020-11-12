package controller.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.UsuarioDAO;
import aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
@WebServlet(name="teste", urlPatterns = {"/teste"})
public class teste extends HttpServlet {
    Connection conexao = null;
    
    public void init() throws ServletException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", "");
            
            System.out.println("Conectei :D");
        } catch(ClassNotFoundException ex){
            System.out.println("Não foi possível encontrar o Driver!");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
            ex.printStackTrace();
        }
    }
    
    @Override
    public void destroy() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
        } catch (Exception ex) {
            System.out.println("errooo");
        } 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        ArrayList<Usuario> lista_usuarios;
        try {
            UsuarioDAO usuario = new UsuarioDAO();
            ArrayList<Usuario> lista_usuarios = usuario.getListaUsuarios();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet teste</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de usuarios:</h1>");            
            for (int i = 0; i < lista_usuarios.size(); i++){
                out.println("<p>OI " + lista_usuarios.get(i).getNome() + "</p>");            
            }
            out.println("</body>");
            out.println("</html>");
            
//            String selectSQL = "SELECT * FROM USUARIO";
//            PreparedStatement preparedStatement = conexao.prepareStatement(selectSQL);
//            ResultSet resultado = preparedStatement.executeQuery();
//            if (resultado != null){
//                
//            }
//             lista_usuarios = Usuario.getListaUsuarios();
//            String nome = request.getParameter("nome");
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet teste</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1> OI MUNDO </h1>");
//            if (nome != null){
//                out.println("<h1>OI " + nome + "</h1>");            
//            }
//            out.println("</body>");
//            out.println("</html>");
            
//        } catch (SQLException ex) {
//            System.out.println("Não foi possível conectar ao banco!");
        } finally {
            out.close();
        }
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
//        RequestDispatcher rd = request.getRequestDispatcher("/ImcView");
//        request.getRequestDispatcher("web/assets/templates/index.html").forward(request, response);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet teste</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet teste at " + request.getContextPath() + "</h1>");
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
