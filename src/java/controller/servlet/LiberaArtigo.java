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
@WebServlet(name = "LiberaArtigo", urlPatterns = {"/LiberaArtigo"})
public class LiberaArtigo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        if (current_user != null && current_user.getPapel() == 1){
            response.setContentType("text/html;charset=UTF-8");
            int artigo_id = Integer.parseInt(request.getParameter("artigo_id"));
            Artigo artigo = new Artigo();
            artigo.liberaArtigo(artigo_id);
            RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos?modo_listagem=usuario");
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
