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
@WebServlet(name = "CriaArtigo", urlPatterns = {"/CriaArtigo"})
public class CriaArtigo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && (current_user.isAdmin() || current_user.isAutor())){
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/novo_artigo.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("error_message", "Apenas usuários do tipo \"Autor\" podem acessar essa tela.");
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && (current_user.isAdmin() || current_user.isAutor())){
            int current_user_id = current_user.getId();
            int categoria_id = Integer.parseInt(request.getParameter("categoria_id"));
            String titulo = request.getParameter("titulo");
            String aprovado = "N";
            String liberar = Boolean.parseBoolean(request.getParameter("liberar")) ? "S" : "N";
            System.out.println(request.getParameter("liberar"));
            String conteudo = request.getParameter("conteudo");

            Artigo artigo = new Artigo();
            artigo.setUsuario(current_user_id);
            artigo.setCategoria(categoria_id);
            artigo.setTitulo(titulo);
            artigo.setConteudo(conteudo);
            artigo.setLiberar(liberar);
            artigo.setAprovado(aprovado);

            boolean sucesso = artigo.salvaArtigo();
            if (sucesso){
                RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
                rd.forward(request, response);
            }
            else{
                RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("error_message", "Apenas usuários do tipo \"Autor\" podem acessar essa tela.");
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
