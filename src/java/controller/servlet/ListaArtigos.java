/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.servlet;

import Aplicacao.Artigo;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaArtigos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListaArtigos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario =  (Usuario) session.getAttribute("current_user");
        int usuario_id = usuario.getId();
      
        response.setContentType("text/html;charset=UTF-8");
        String modo_listagem = "publicados";
        if (request.getParameter("modo_listagem") != null){
            modo_listagem = request.getParameter("modo_listagem");
        }
        Artigo artigo = new Artigo();
        List<Artigo> lista_artigos = null;
        String titulo = "Artigos";
        if (modo_listagem.equals("pendentes")){
            lista_artigos = artigo.listaArtigosPendentes();
            titulo = "Artigos pendentes";
        }
        if (modo_listagem.equals("publicados")){
            lista_artigos = artigo.listaArtigosPublicados();
            titulo = "Artigos publicados";
        }
        if (modo_listagem.equals("usuario")){
            lista_artigos = artigo.listaArtigosPorUsuario(usuario_id);
            titulo = "Meus Artigos";
        } 
        request.setAttribute("lista_artigos", lista_artigos);
        request.setAttribute("titulo", titulo);
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
