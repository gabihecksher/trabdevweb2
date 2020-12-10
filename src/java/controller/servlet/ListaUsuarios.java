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
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
@WebServlet(name = "ListaUsuarios", urlPatterns = {"/ListaUsuarios"})
public class ListaUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            HttpSession session = request.getSession();
            Usuario current_user =  (Usuario) session.getAttribute("current_user");
            String titulo = "Usuários";
            String modo_listagem = "aprovados";
            Usuario usuario = new Usuario();
            List<Usuario> lista_usuarios = null;
            System.out.println("procura usuarios" + request.getParameter("modo_listagem"));
            if (request.getParameter("modo_listagem") != null){
                modo_listagem = request.getParameter("modo_listagem");
            }
            if (modo_listagem.equals("pendentes")){
                if(current_user != null && current_user.isAdmin()){
                    lista_usuarios = usuario.getListaUsuariosPorStatus(false);
                    titulo = "Usuários pendentes";
                    request.setAttribute("titulo", titulo);
                }
                else{
                    request.setAttribute("error_message", "Apenas usuários do tipo \"Administrador\" podem acessar essa tela.");
                    RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                    rd.forward(request, response);
                }
            }
            else if (modo_listagem.equals("aprovados")){
                lista_usuarios = usuario.getListaUsuariosPorStatus(true);
                titulo = "Usuários aprovados";
                request.setAttribute("titulo", titulo);
            }
            request.setAttribute("lista_usuarios", lista_usuarios);
            
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/list_user.jsp");
            rd.forward(request, response);
        }  catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
            ex.printStackTrace();
        }

    }
            

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try{
            Usuario current_user =  (Usuario) session.getAttribute("current_user");
            if (current_user != null){
                Usuario usuario = new Usuario();
                List<Usuario> lista_usuarios = null;
                lista_usuarios = usuario.getListaUsuariosPorStatus(true);
                request.setAttribute("lista_usuarios", lista_usuarios);
                String titulo = "Usuários aprovados";
                request.setAttribute("titulo", titulo);
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/list_user.jsp");
                rd.forward(request, response);
            }
            else {
                response.sendRedirect("Login");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco!");
            ex.printStackTrace();
        }
    }


}
