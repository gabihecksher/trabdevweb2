/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import Aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("assets/templates/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession current_session = request.getSession();
        Usuario current_user =  (Usuario) current_session.getAttribute("current_user");
        if (current_user == null){

            String cpf_user = request.getParameter("legal_id");
            String senha_user = request.getParameter("password");

            if (cpf_user.isEmpty()){
                request.setAttribute("error_message", "Por favor preencha o campo de CPF.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
            else if (senha_user.isEmpty()){
                request.setAttribute("error_message", "Por favor preencha o campo de senha.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
            Usuario user = new Usuario();
            user = user.getUsuarioPorLoginSenha(cpf_user, senha_user);
            if (user != null && user.getCadastroAprovado().equals("S")){
                HttpSession session = request.getSession();
                session.setAttribute("NomeUsuarioLogado", user.getNome());
                session.setAttribute("current_user", user);
                session.setAttribute("SessaoAtiva", true);

                request.setAttribute("user", session.getAttribute("NomeUsuarioLogado"));
                System.out.println("Nome da sessão: " + session.getAttribute("NomeUsuarioLogado"));
                RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
                rd.forward(request, response);
            } 
            else if (user != null){
                request.setAttribute("error_message", "Usuário pendente de aprovação.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
            else {
                request.setAttribute("error_message", "CPF ou senha incorretos.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("error_message", "Você já está logado.");
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
            rd.forward(request, response);
        }
    }
}
