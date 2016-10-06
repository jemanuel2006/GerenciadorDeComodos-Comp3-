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
    <title>Alterar Quarto</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../www/toastr/build/toastr.min.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="../www/toastr/build/toastr.min.js"></script>
    <script type="application/javascript" src="../ToastrConfig.js"></script>
</head>
<body>
    <jsp:include page="../NavBarGerenciador.jsp"></jsp:include>
    <%
        boolean showMessage = false;
        String message = "";
        if(session.getAttribute("success") != null){
            showMessage = Boolean.parseBoolean(session.getAttribute("success").toString());
            message = session.getAttribute("message").toString();

            session.removeAttribute("success");
            session.removeAttribute("message");
        }
    %>
    <% if(showMessage){ %>
    <script type="application/javascript">
        toastr.success("<%=message%>")
    </script>
    <%}%>
    <div class="container">
        <legend>Alterar Quarto</legend>
        <div class="row">
            <form action="/Edit/Quarto" method="post">
                <input name="hfId" id="hfId" type="hidden" value="${hfId}" />
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label" for="description">* Descrição:</label>
                        <input class="form-control" required name="description" id="description" type="text"  value="${description}"/>
                    </div>
                    <button class="btn btn-primary" type="submit">Alterar Quarto</button>
                    <a class="btn btn-danger" href="/List/Quarto">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
