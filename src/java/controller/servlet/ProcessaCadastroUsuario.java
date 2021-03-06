/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

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
@WebServlet(name = "ProcessaCadastroUsuario", urlPatterns = {"/ProcessaCadastroUsuario"})
public class ProcessaCadastroUsuario extends HttpServlet {
    Connection connection = null;
    
    @Override
    public void init() throws ServletException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", "");
            
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
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("assets/templates/add_user.html");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String nome = request.getParameter("full_name");
            String campo_obrigatorio_vazio = "";
            String email = request.getParameter("email");
            String cpf = request.getParameter("legal_id");
            String categoria = request.getParameter("category");
            String senha = request.getParameter("password");
            String confirmacao_senha = request.getParameter("password_confirmation");
            System.out.println("---------------------------");
            System.out.println("---------------------------");
            System.out.println("---------------------------");
            System.out.println(nome);
            System.out.println(email);
            System.out.println(cpf);
            System.out.println(categoria);
            System.out.println(senha);
            System.out.println(confirmacao_senha);
            if (nome.isEmpty()){
                campo_obrigatorio_vazio = "nome";
            }
            else if (email.isEmpty()){
                campo_obrigatorio_vazio = "e-mail";
            }
            else if (cpf.isEmpty()){
                campo_obrigatorio_vazio = "CPF";
            }
            else if (categoria.isEmpty()){
                campo_obrigatorio_vazio = "Papel";
            }
            else if (senha.isEmpty()){
                campo_obrigatorio_vazio = "Senha";
            }
            else if (confirmacao_senha.isEmpty()){
                campo_obrigatorio_vazio = "Confirmação de senha";
            }
            if (!campo_obrigatorio_vazio.isEmpty()){
                out.println("<script type='text/javascript'>");
                out.println("alert(" + "'Preencha o campo " + campo_obrigatorio_vazio + "'" + ");</script>");
                out.println("</head><body></body></html>");
            }
            if (senha != confirmacao_senha){
                out.println("<script type='text/javascript'>");
                out.println("alert(" + "'Preencha o campo " + campo_obrigatorio_vazio + "'" + ");</script>");
                out.println("</head><body></body></html>");
            }
            else{
                String stringSQL = "INSERT INTO Usuario (nome, email, cpf, papel, senha) VALUES(?,?,?,?,?)";
                PreparedStatement sql = connection.prepareStatement(stringSQL);
                sql.setString(1, nome);
                sql.setString(2, email);
                sql.setString(3, cpf);
                sql.setInt(4, Integer.parseInt(categoria));
                sql.setString(5, senha);
                System.out.println("vai executar o sql");
                sql.executeUpdate();
                System.out.println("foi!");
                response.sendRedirect("teste");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
        } finally {
            out.close();
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
