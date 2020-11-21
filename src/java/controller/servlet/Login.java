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
        String cpf_user = request.getParameter("legal_id");
        String senha_user = request.getParameter("password");

        Usuario user = new Usuario();
        user = user.getUsuarioPorLoginSenha(cpf_user, senha_user);
        System.out.println(cpf_user);
        System.out.println(senha_user);
        System.out.println(user.getNome());
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("NomeUsuarioLogado", user.getNome());
            session.setAttribute("SessaoAtiva", true);
            
            request.setAttribute("user", session.getAttribute("NomeUsuarioLogado"));
            System.out.println("Nome da sessão: " + session.getAttribute("NomeUsuarioLogado"));
            RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
            rd.forward(request, response);
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert(" + "'Login inválido'" + ");</script>");
            out.println("</head><body></body></html>");
        }
    }
}
