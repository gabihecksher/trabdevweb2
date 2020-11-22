<%@page import="java.util.List"%>
<%@page import="Aplicacao.Artigo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title><%= request.getAttribute("titulo") %></title>

        <jsp:include page="head.html" />
    </head>

	<body>	
            <jsp:include page="navbar.jsp" />
            
            <p>Usuário logado: <%= request.getAttribute("user") %></p>
            <div class="container list-container">
		<div class="list-page-title">
                    <h3>Artigos</h3>
		</div>
                <%  
                    List<Artigo> ListaArtigo = (List<Artigo>) request.getAttribute("lista_artigos");
                    System.out.println(ListaArtigo);
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
                    
                    <% 
                        boolean artigosPendentes = "pendentes".equals(request.getParameter("modo_listagem"));
                        if (artigosPendentes){ %>
                    <div class="card-footer horizontal-align-center">
                        <a href="AprovaArtigo?artigo_id=<%=artigo.getId()%>"class="button-approve">Aprovar<i class="fas fa-thumbs-up"></i></a>
                    </div>
                    <% } %>
                    
		</div>
		<% }} %>		
                
                <% if("usuario".equals(request.getParameter("modo_listagem"))){ %>
		<div class="horizontal-align-center">
                    <a class="btn btn-primary button-approve" href="CriaArtigo" role="button">Adicionar<i class="fas fa-plus-square"></i></a>
		</div>
                <% } %>
                
            </div>
	</body>
</html>