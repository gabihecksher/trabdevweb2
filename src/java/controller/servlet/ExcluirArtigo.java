package Controller.servlet;

import Aplicacao.Artigo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ExcluirArtigo", urlPatterns = {"/ExcluirArtigo"})
public class ExcluirArtigo extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int artigo_id =  Integer.parseInt(request.getParameter("artigo_id"));
        System.out.println(artigo_id);
        new Artigo().excluirArtigo(artigo_id);
        RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos");
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
