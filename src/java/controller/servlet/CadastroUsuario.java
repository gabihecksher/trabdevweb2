/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author gabri
 */
@WebServlet(name = "CadastroUsuario", urlPatterns = {"/CadastroUsuario"})
public class CadastroUsuario extends HttpServlet {
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
        RequestDispatcher rd = request.getRequestDispatcher("assets/templates/add_user.jsp");
        rd.forward(request, response);
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
            String papel = request.getParameter("category");
            String senha = request.getParameter("password");
            String confirmacao_senha = request.getParameter("password_confirmation");
            if (nome.isEmpty()){
                campo_obrigatorio_vazio = "nome";
            }
            else if (email.isEmpty()){
                campo_obrigatorio_vazio = "e-mail";
            }
            else if (cpf.isEmpty()){
                campo_obrigatorio_vazio = "CPF";
            }
            else if (papel.isEmpty()){
                campo_obrigatorio_vazio = "Papel";
            }
            else if (senha.isEmpty()){
                campo_obrigatorio_vazio = "Senha";
            }
            else if (confirmacao_senha.isEmpty()){
                campo_obrigatorio_vazio = "Confirmação de senha";
            }
            if (!campo_obrigatorio_vazio.isEmpty()){
                request.setAttribute("error_message", "Preencha o campo obrigatório " + campo_obrigatorio_vazio + ".");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
            if (!senha.equals(confirmacao_senha)){
                request.setAttribute("error_message", "A senha e a confirmação da senha não estão iguais.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            } 
            else{
                String stringSQL = "INSERT INTO Usuario (nome, email, cpf, papel, senha, cadastro_aprovado) VALUES(?,?,?,?,?,?)";
                PreparedStatement sql = connection.prepareStatement(stringSQL);
                sql.setString(1, nome);
                sql.setString(2, email);
                sql.setString(3, cpf);
                sql.setInt(4, Integer.parseInt(papel));
                sql.setString(5, senha);
                sql.setString(6, "N");
                System.out.println("vai executar o sql");
                sql.executeUpdate();
                System.out.println("foi!");
                response.sendRedirect("ListaUsuarios");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
        } finally {
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}