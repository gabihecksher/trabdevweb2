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
@WebServlet(name = "ExcluiComentario", urlPatterns = {"/ExcluiComentario"})
public class ExcluiComentario extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && (current_user.isComentarista() || current_user.isAdmin())){
            int current_user_id = current_user.getId();
            int comentario_id = Integer.parseInt(request.getParameter("id"));
            System.out.println(comentario_id);
            boolean sucesso = true;
            Comentario comentario = new Comentario().getComentarioPorId(comentario_id);
            System.out.println(current_user_id);
            System.out.println(comentario.getUsuario());
                    
            if(comentario.getUsuario().getId() == current_user_id || current_user.isAdmin()){
                sucesso = comentario.excluir(comentario_id);
                if (sucesso){
                    System.out.println("sucessooo");
                    RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
                    rd.forward(request, response);
                } else{
                    request.setAttribute("error_message", "Ocorreu um erro ao excluir o comentário.");
                    RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                    rd.forward(request, response);
                }
            } else{
                request.setAttribute("error_message", "Apenas administradores ou o próprio autor do comentário podem excluir ele.");
                RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                rd.forward(request, response);
            }
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
        if (current_user != null && current_user.isComentarista()){
            int comentario_id = Integer.parseInt(request.getParameter("comentario_id"));
            System.out.println(comentario_id);

            Comentario comentario = new Comentario();
            boolean sucesso = comentario.excluir(comentario_id);

            if (sucesso){
                RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("error_message", "Ocorreu um erro ao excluir o comentário.");
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
