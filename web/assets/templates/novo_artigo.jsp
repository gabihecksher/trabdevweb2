<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.html" />
    </head>
    <body>
        <jsp:include page="navbar.html" />
        
        <div class="vertical-center">
            <div class="container col-md-6 col-md-offset-6">
                <div class="row content justify-content-md-center">
                    <div class="col-12">
                        <div class="page-title">
                            <h3>Criar Artigo</h3>
                        </div>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-10">
                        <jsp:include page="form_artigo.jsp" />
                    </div> 
                    <div class="col-1"></div>					
                </div>
            </div>
        </div>
    </body>
</html>
