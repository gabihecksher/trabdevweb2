<%@page import="java.util.List"%>
<%@page import="Aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Login</title>
        <jsp:include page="head.html" />
        
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        
        <div class="vertical-center">
            <div class="container col-md-4 col-md-offset-4">
                <fieldset>
                        <div class="row content justify-content-md-center">
                            <div class="col-8">
                                <h3>Erro</h3>
                                <p><%= request.getAttribute("error_message") %></p>
                            </div>  
                        </div>
                </fieldset>
            </div>
        </div>
    </body>
</html>

