<%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 18/09/2016
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Sala</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="../NavBarGerenciador.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <form action="/Create/Sala" method="post">
                <legend>Criar Sala</legend>
                    <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label" for="description">* Descrição:</label>
                        <input class="form-control" required name="description" id="description" type="text"/>
                    </div>
                    <button class="btn btn-primary" type="submit">Criar Sala</button>
                    <a class="btn btn-danger" href="/List/Sala">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
