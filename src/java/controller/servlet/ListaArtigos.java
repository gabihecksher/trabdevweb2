/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import Aplicacao.Artigo;
import Aplicacao.Comentario;
import Aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mreis
 */
@WebServlet(name = "ListaArtigos", urlPatterns = {"/ListaArtigos"})
public class ListaArtigos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        System.out.println("-----------------");
        System.out.println("Usuario:");
        System.out.println(current_user);
        
        response.setContentType("text/html;charset=UTF-8");
        String modo_listagem = "publicados";
        if (request.getParameter("modo_listagem") != null){
            modo_listagem = request.getParameter("modo_listagem");
        }
        System.out.println(modo_listagem);
        
        Artigo artigo = new Artigo();
        List<Artigo> lista_artigos = null;
        String titulo = "Artigos";
        if (modo_listagem.equals("publicados")){
            lista_artigos = artigo.listaArtigosPublicados();
            titulo = "Artigos publicados";
            System.out.println(titulo);
        }
        else{
            if (modo_listagem.equals("pendentes")){
                if (current_user != null && (current_user.getPapel() == 0 || current_user.getPapel() == 1)){
                    lista_artigos = artigo.listaArtigosPendentes();
                    titulo = "Artigos pendentes";
                }
                else {
                    request.setAttribute("error_message", "Apenas usuários do tipo \"Administrador\" podem acessar essa tela.");
                    RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                    rd.forward(request, response);
                }
            }
            if (modo_listagem.equals("usuario")){
                if (current_user != null && current_user.getPapel() == 1){
                    int current_user_id = current_user.getId();
                    lista_artigos = artigo.listaArtigosPorUsuario(current_user_id);
                    titulo = "Meus Artigos";
                }
                else {
                    request.setAttribute("error_message", "Apenas usuários do tipo \"Autor\" podem acessar essa tela.");
                    RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
                    rd.forward(request, response);
                }
            }
        }
        
        List<Integer> id_artigos = new ArrayList<Integer>();
        if(lista_artigos != null){
            for(Artigo temp_artigo : lista_artigos){
                id_artigos.add(temp_artigo.getId());
            }
        }
        System.out.println(id_artigos);
        Comentario comentario = new Comentario();
        List<Comentario> lista_comentarios = new ArrayList<>();
        lista_comentarios = comentario.listarComentarioPorArtigos(id_artigos);
        
        request.setAttribute("lista_artigos", lista_artigos);
        request.setAttribute("lista_comentarios", lista_comentarios);
        request.setAttribute("titulo", titulo);
        System.out.println(lista_artigos);
        System.out.println(lista_comentarios);
        System.out.println(titulo);
        
        RequestDispatcher rd = request.getRequestDispatcher("assets/templates/list_article.jsp");
        rd.forward(request, response);
        
    }
            

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
