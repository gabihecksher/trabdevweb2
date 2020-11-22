package Controller.servlet;

import Aplicacao.Artigo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AprovaArtigo", urlPatterns = {"/AprovaArtigo"})
public class AprovaArtigo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int artigo_id = Integer.parseInt(request.getParameter("artigo_id"));
        Artigo artigo = new Artigo();
        artigo.aprovaArtigo(artigo_id);
        RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos?modo_listagem=pendentes");
        rd.forward(request, response);
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
