<%@page import="java.util.List"%>
<%@page import="Aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand nav-button nav-home-button nav-item" href="list_article.html">Alpha Blog</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"><i class="fas fa-bars"></i></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Artigos
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="ListaArtigos?modo_listagem=publicados"">Artigos publicados</a>
                    <a class="dropdown-item" href="ListaArtigos?modo_listagem=pendentes">Artigos pendentes</a>
                    <a class="dropdown-item" href="ListaArtigos?modo_listagem=usuario">Meus artigos</a>
                    
                    <a class="dropdown-item" href="CriaArtigo">Criar artigo</a>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Categorias
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="list_article.html">Artes</a>
                    <a class="dropdown-item" href="list_article.html">Política</a>
                    <a class="dropdown-item" href="list_article.html">Ficção</a>
                    <a class="dropdown-item" href="add_category.html">Criar categoria</a>
                </div>
            </li>

            <li class="nav-item dropdown last-nav-link">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Usuários
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="list_user.html">Usuários pendentes</a>
                    <a class="dropdown-item" href="add_user.html">Criar usuário</a>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav">
            <%
                if (request.getAttribute("user") != null){
                
            %>
            <li class="nav-item dropdown last-nav-link">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%= request.getAttribute("user") %> <i class="fas fa-user"></i>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="list_user.html">Perfil</a>
                    <a class="dropdown-item" href="Logout">Logout</a>
                </div>
            </li>
            <% } else{ %>
            <li><a class="nav-link" href="<% response.sendRedirect("Logout"); %>">Login <i class="fas fa-user"></i></a></li>
            <% } %>
        </ul>
    </div>
</nav>
