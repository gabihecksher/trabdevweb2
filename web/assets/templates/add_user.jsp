<%@page import="java.util.List"%>
<%@page import="Aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Usuário</title>
        <jsp:include page="head.html" />
        
    </head>
    <body>
        <jsp:include page="navbar.html" />
        
        <div class="vertical-center">
            <div class="container col-md-4 col-md-offset-4">
                <div class="row content justify-content-md-center">
                    <div class="col-12">
                        <div class="page-title">
                            <h3>Cadastrar usuário</h3>
                        </div>
                    </div>
                    <div id="errors"></div>
                    <div class="col-8">
                        <form class="form" method="post" action="http://localhost:8080/AlphaBlog/CadastroUsuario">
                            <div class="form-group row">
                                <input type="text" class="form-control form-control-md" placeholder="Nome" name="full_name">
                            </div>
                            <div class="form-group row">
                                <input type="email" class="form-control form-control-md" name="email" placeholder="E-mail">
                            </div>
                            <div class="form-group row">
                                <input type="text" class="form-control form-control-md" name="legal_id" placeholder="CPF">
                            </div>
                            <div class="form-group row">
                                <select name="category" class="form-control form-control-md">
                                    <option value="" disabled selected>Papel</option> 
                                    <option value="0">Administrador</option> 
                                    <option value="1">Autor</option>
                                    <option value="2">Comentarista</option>
                                </select>
                            </div>
                            <div class="form-group row">
                                <input type="password" class="form-control form-control-md input" placeholder="Senha" name="password">
                            </div>
                            <div class="form-group row">
                                <input type="password" class="form-control form-control-md input" placeholder="Confirmação de senha" name="password_confirmation">
                            </div>
                            <div class="row justify-content-md-center">
                                <input class="btn save-button" type="submit" value="Salvar"/>
                            </div>
                        </form>
                    </div>  
                </div>
                
            </div>
        </div>
    </body>
</html>

