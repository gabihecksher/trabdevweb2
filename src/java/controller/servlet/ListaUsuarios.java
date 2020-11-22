/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import Aplicacao.Usuario;
import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author gabri
 */
@WebServlet(name = "ListaUsuarios", urlPatterns = {"/ListaUsuarios"})
public class ListaUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListaUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String titulo = "Usuários";
            String modo_listagem = "aprovados";
            Usuario usuario = new Usuario();
            List<Usuario> lista_usuarios = null;
            System.out.println("procura usuarios" + request.getParameter("modo_listagem"));
            if (request.getParameter("modo_listagem") != null){
                modo_listagem = request.getParameter("modo_listagem");
            }
            if (modo_listagem.equals("pendentes")){
                lista_usuarios = usuario.getListaUsuariosPorStatus(false);
                titulo = "Usuários pendentes";
            }
            else if (modo_listagem.equals("aprovados")){
                lista_usuarios = usuario.getListaUsuariosPorStatus(true);
                titulo = "Usuários aprovados";
            }
            System.out.println("achou usuarios" + request.getParameter("modo_listagem"));
            
            request.setAttribute("lista_usuarios", lista_usuarios);
            System.out.println("usuarios:" + lista_usuarios);
            
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/list_user.jsp");
            rd.forward(request, response);
            System.out.println("foi");
            
        }  catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
            ex.printStackTrace();
        }

    }
            

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
