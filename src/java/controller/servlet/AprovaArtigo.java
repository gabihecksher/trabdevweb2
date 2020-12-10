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


@WebServlet(name = "AprovaArtigo", urlPatterns = {"/AprovaArtigo"})
public class AprovaArtigo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario current_user =  (Usuario) session.getAttribute("current_user");
        System.out.println("-------------");
        System.out.println("ENTROU");
        if (current_user != null && current_user.isAdmin()){
            response.setContentType("text/html;charset=UTF-8");
            int artigo_id = Integer.parseInt(request.getParameter("artigo_id"));
            String aprovado = request.getParameter("aprovado");
            Artigo artigo = new Artigo();
            if (aprovado.equals("N")){
                System.out.println("DESAPROVADO");
                System.out.println(artigo_id);
                artigo.excluirArtigo(artigo_id);
            }
            else{
                System.out.println("APROVADO");
                artigo.aprovaArtigo(artigo_id, aprovado);
            }
            RequestDispatcher rd = request.getRequestDispatcher("ListaArtigos?modo_listagem=pendentes");
            rd.forward(request, response);
        }
        else {
            request.setAttribute("error_message", "Apenas usuários do tipo \"Administrador\" podem acessar essa tela.");
            RequestDispatcher rd = request.getRequestDispatcher("assets/templates/error.jsp");
            rd.forward(request, response);
        }
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
