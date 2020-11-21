<%@page import="java.util.List"%>
<%@page import="Aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Usuários</title> 
        <jsp:include page="head.html" />
        
    </head>

	<body>
            <jsp:include page="navbar.html" />
            <div class="container list-container">
		<div class="list-page-title">
                    <h3>Usuários</h3>
		</div>
			
		<%
                    List<Usuario> ListaUsuario = (List<Usuario>) request.getAttribute("lista_usuarios");
                    for (int i = 0; i < ListaUsuario.size(); i++) {
                        Usuario usuario = ListaUsuario.get(i);
                %>
		<div class="card">
                    <div class="card-body">
                        <div class="row">
                            <h4 class="col-md-10 card-title"><%=usuario.getNome()%></h4>
                            <div class="col-md-2">
                                <a class="btn button-edit btn-warning" href="edit_user.html" role="button">Editar <i class="fas fa-edit"></i></a>
				<button class="button-delete btn btn-danger">Excluir <i class="fas fa-trash-alt"></i></button>
                            </div>
			</div>
			<p class="card-text">
                            <small class="text-muted">
				<span class="glyphicon glyphicon-user"></span>
				<%=usuario.getEmail()%>
				</small>
			</p>
						
                        <p class="card-text">
                            <small class="text-muted">
				<span class="glyphicon glyphicon-user"></span>
					<%=usuario.getPapelNome()%>
				</small>
			</p>
						
			<p class="card-text">
                            <small class="text-muted">
                            <span class="glyphicon glyphicon-user"></span>
				<%=usuario.getCpf()%>
                            </small>
			</p>

                    </div>
                    <div class="card-footer horizontal-align-center">
			<button class="button-approve">Aprovar<i class="fas fa-thumbs-up"></i></button>
			<button class="button-reprove">Reprovar<i class="fas fa-thumbs-down"></i></button>
                    </div>
                </div>
		<% } %>		
		<div class="horizontal-align-center">
                    <a class="btn btn-primary button-approve" href="add_user.html" role="button">Adicionar<i class="fas fa-plus-square"></i></a>
		</div>
				
            </div>	
		
	</body>
</html>
