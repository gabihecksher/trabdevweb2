/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import Aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AprovaUsuario", urlPatterns = {"/AprovaUsuario"})
public class AprovaUsuario extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && current_user.isAdmin()){
            response.setContentType("text/html;charset=UTF-8");
            int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
            Usuario usuario = new Usuario();
            usuario.aprovaUsuario(usuario_id);
            usuario.setCadastroAprovado("S");
            RequestDispatcher rd = request.getRequestDispatcher("ListaUsuarios?modo_listagem=pendentes");
            rd.forward(request, response);
        }
        else {
            request.setAttribute("error_message", "Apenas usuários do tipo \"Administrador\" podem acessar essa tela.");
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
