<%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 18/09/2016
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Quarto</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="../NavBarGerenciador.jsp"></jsp:include>
    <div class="container">
        <legend>Criar Quarto</legend>
        <div class="row">
            <form action="/Create/Quarto" method="post">
                <div class="col-md-5">
                    <div class="form-group">
                        <label class="control-label" for="description">Descrição:</label>
                        <input class="form-control" required name="description" id="description" type="text"/>
                    </div>
                    <button class="btn btn-primary" type="submit">Criar Quarto</button>
                    <a class="btn btn-danger" href="/List/Quarto">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
