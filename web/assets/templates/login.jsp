<%@page import="java.util.List"%>
<%@page import="Aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Login</title>
        <jsp:include page="head.html" />
        
    </head>
    <body>
        <jsp:include page="navbar.html" />
        
        <div class="vertical-center">
            <div class="container col-md-4 col-md-offset-4">
                <fieldset>
                        <legend>Alpha Blog</legend>
                        <div class="row content justify-content-md-center">
                            <div class="col-8">
                                <form class="form" method="post" action="http://localhost:8080/AlphaBlog/Login">
                                    <div class="form-group row">
                                        <div class="col-sm input-wrapper-legal-id">
                                            <input type="text" class="form-control form-control-md cpf-mask input" placeholder="CPF" name="legal_id" required="required" id="cpf">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm input-wrapper-password">
                                            <input type="password" class="form-control form-control-md input" placeholder="Senha" name="password" required="required">
                                        </div>
                                    </div>
                                    <div class="row justify-content-md-center">
                                        <!--<button type="submit" class="btn btn-default">Entrar</button>-->
                                        <input class="btn save-button" type="submit" value="Entrar"/>
                                    </div>
                                    <a href="add_user.html">Não tem usuário? Cadastre-se aqui.</a>
                                </form>
                            </div>  
                        </div>
                </fieldset>
            </div>
        </div>
    </body>
</html>

