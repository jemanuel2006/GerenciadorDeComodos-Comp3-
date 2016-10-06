<%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 05/09/2016
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Cozinha</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="../NavBarGerenciador.jsp"></jsp:include>
    <div class="container">
        <legend>Criar Cozinha</legend>
        <div class="row">
            <form action="/Create/Cozinha" method="post">
                <div class="col-md-5">
                    <div class="form-group">
                        <label class="control-label" for="description">Descrição:</label>
                        <input class="form-control" required name="description" id="description" type="text"/>
                    </div>
                    <button class="btn btn-primary" type="submit">Criar Cozinha</button>
                    <a class="btn btn-danger" href="/List/Cozinha">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
