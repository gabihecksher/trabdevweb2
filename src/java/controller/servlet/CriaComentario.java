/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import Aplicacao.Comentario;
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
@WebServlet(name = "CriaComentario", urlPatterns = {"/CriaComentario"})
public class CriaComentario extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && (current_user.isAdmin() || current_user.isComentarista())){
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/list_article.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("error_message", "Apenas usuários do tipo \"Comentarista\" ou \"Administrador\" podem criar um comentário.");
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && (current_user.isAdmin() || current_user.isComentarista())){
            int current_user_id = current_user.getId();
            int artigo_id = Integer.parseInt(request.getParameter("artigo_id"));
            String texto = request.getParameter("texto");
            
            Comentario comentario = new Comentario();
            comentario.setUsuario(current_user_id);
            comentario.setArtigo(artigo_id);
            comentario.setComentario(texto);
            
            boolean sucesso = comentario.salvarComentario();
            if (sucesso){
                RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("error_message", "Ocorreu um erro ao criar o comentário.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("error_message", "Apenas usuários do tipo \"Comentarista\" ou \"Administrador\" podem criar um comentário.");
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
            rd.forward(request, response);
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
