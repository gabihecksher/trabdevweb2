<%@page import="java.util.List"%>
<%@page import="Aplicacao.Artigo"%>
<%@page import="Aplicacao.Usuario"%>
<%@page import="Aplicacao.Comentario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    Usuario user = null;
    if (request.getSession().getAttribute("current_user") != null){
        user = (Usuario) request.getSession().getAttribute("current_user");
    }
%>
        
<html>
    <head>
        <title><%= request.getAttribute("titulo") %></title>

        <jsp:include page="head.html" />
    </head>

    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container list-container">
            <div class="list-page-title">
                <h3>Artigos</h3>
            </div>
            <%  
                List<Artigo> ListaArtigo = (List<Artigo>) request.getAttribute("lista_artigos");
                if (ListaArtigo != null && ListaArtigo.size() > 0){
                    for (int i = 0; i < ListaArtigo.size(); i++) {
                        Artigo artigo = ListaArtigo.get(i);
            %>
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <h4 class="col-md-10 card-title"><a href="index.html"><%=artigo.getTitulo()%></a></h4>
                                    <div class="col-md-2">
                                        <button class="button-edit btn btn-warning">Editar <i class="fas fa-edit"></i></button>
                                        <button class="button-delete btn btn-danger">Excluir <i class="fas fa-trash-alt"></i></button>
                                    </div>
                                </div>
                                <p class="card-text">
                                    <small class="text-muted">
                                        <span class="glyphicon glyphicon-user"></span>
                                            <%=artigo.getUsuario().getNome()%>
                                    </small>
                                </p>			
                                <p class="card-text">
                                    <small class="text-muted">
                                        <span class="glyphicon glyphicon-user"></span>
                                        <%=artigo.getCategoria().getDescricao()%>
                                    </small>
                                </p>

                                <div class="card-text article_text">
                                    <%=artigo.getConteudo()%>
                                </div>
                            </div>

                            <div class="card-footer">
                                <div class="comment-section">
                                    Comentários
                                <%  
                                    List<Comentario> ListaComentario = (List<Comentario>) request.getAttribute("lista_comentarios");
                                    System.out.println(ListaComentario);
                                    if (ListaComentario != null && ListaComentario.size() > 0){
                                        for (int j = 0; j < ListaComentario.size(); j++) {
                                            Comentario comentario = ListaComentario.get(j);
                                            if (comentario.getArtigo().getId() == artigo.getId()){
                                %>
                                                <div class="comment">
                                                    <div class="comment-head">
                                                        <div class="row">
                                                            <div class="col-md-10">
                                                                <i class="fas fa-user"></i>
                                                                <%= comentario.getUsuario().getNome() %>
                                                            </div>
                                                            <% 
                                                                if (user != null && comentario.getUsuario().getId() == user.getId()){

                                                            %>
                                                                <div class="col-md-2">
                                                                    <button class="button-edit btn btn-warning">Editar <i class="fas fa-edit"></i></button>
                                                                    <a class="button-delete btn btn-danger" href="ExcluiComentario?id=<%= comentario.getId() %>">Excluir <i class="fas fa-trash-alt"></i></a>
                                                                </div>
                                                            <%
                                                                }
                                                            %>
                                                        </div>
                                                    </div>
                                                    <div class="comment-body">
                                                        <div class="card-text article_text">
                                                            <%= comentario.getComentario() %>
                                                        </div>
                                                    </div>
                                                </div>
                                <% 
                                            }
                                        }
                                    }
                                    if (user != null && user.getPapel() == 2) {
                                %>
                                        <div class="container">
                                            <div class="row content justify-content-md-center">
                                                <div class="new-comment col-12">
                                                    <form class="form" method="post" action="http://localhost:8080/AlphaBlog/CriaComentario">
                                                        <input type="hidden" class="form-control form-control-md" name="artigo_id" value="<%= artigo.getId() %>">
                                                        <div class="form-group row">
                                                            <textarea class="form-control form-control-md" name="texto" required="required" placeholder="Escreva um comentário." rows="5" cols="1"></textarea>
                                                        </div>
                                                        <div class="horizontal-align-center">
                                                            <input class="btn save-button" type="submit" value="Salvar"/>
                                                        </div>
                                                    </form>
                                                </div> 					
                                            </div>
                                        </div>
                                <%
                                    }
                                %>
                                </div>
                            </div>
                            <% 
                                boolean artigosPendentes = "pendentes".equals(request.getParameter("modo_listagem"));
                                if (artigosPendentes && user.getPapel() == 0){ 
                            %>
                                    <div class="card-footer horizontal-align-center">
                                        <a href="AprovaArtigo?artigo_id=<%=artigo.getId()%>&aprovado=S" class="button-approve">Aprovar<i class="fas fa-thumbs-up"></i></a>
                                        <a href="AprovaArtigo?artigo_id=<%=artigo.getId()%>&aprovado=N" class="button-reprove">Reprovar<i class="fas fa-thumbs-down"></i></a>
                                    </div>
                            <% 
                                }
                                boolean artigosUsuario = "usuario".equals(request.getParameter("modo_listagem"));
                                boolean podeLiberar = "N".equals(artigo.getLiberar());
                                if (artigosUsuario && podeLiberar && user.getPapel() == 1){ 
                            %>
                                    <div class="card-footer horizontal-align-center">
                                        <a href="LiberaArtigo?artigo_id=<%=artigo.getId()%>"class="button-approve">Liberar para publicação<i class="fas fa-thumbs-up"></i></a>
                                    </div>
                            <% 
                                }
                            %>

                        </div>
            <% 
                    }
                }
            %>		
                
            <% if("usuario".equals(request.getParameter("modo_listagem"))){ %>
		<div class="horizontal-align-center">
                    <a class="btn btn-primary button-approve" href="CriaArtigo" role="button">Adicionar<i class="fas fa-plus-square"></i></a>
		</div>
            <% } %>
                
        </div>
    </body>
</html>