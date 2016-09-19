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
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <form action="/Salas" method="post">
            <h1>Criar Sala</h1>
                <div class="col-md-5">
                <div class="form-group">
                    <label class="control-label" for="description">Descrição:</label>
                    <input class="form-control" name="description" id="description" type="text"/>
                </div>
            <button class="btn btn-primary" type="submit">Criar Sala</button>
            </div>
        </form>
    </div>
</body>
</html>
