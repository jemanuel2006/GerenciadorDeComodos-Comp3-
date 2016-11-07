<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 05/09/2016
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alterar Mobília</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../www/DataTables/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../www/DataTables/css/dataTables.select.css">
    <link rel="stylesheet" type="text/css" href="../www/toastr/build/toastr.min.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="../www/DataTables/js/jquery.dataTables.min.js"></script>
    <script type="application/javascript" src="../www/DataTables/js/dataTables.select.js"></script>
    <script type="application/javascript" src="../www/DataTables/js/dataTables.bootstrap.min.js"></script>
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
    <legend>Alterar Mobília</legend>
    <div class="row">
        <form action="/Edit/Mobilia" method="post">
            <div class="col-md-8">
                <input name="hfId" id="hfId" type="hidden" value="${hfId}" />
                <input type="hidden" id="hfComodosIds" name="hfComodosIds" value="${hfComodosIds}">
                <div class="form-group">
                    <label class="control-label" for="description">* Descrição:</label>
                    <input class="form-control" required name="description" id="description" type="text" value="${description}"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="cost">* Custo de Produção:</label>
                    <input class="form-control" required name="cost" id="cost" min="0" type="number" value="${cost}"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="deliveryTime">* Tempo de Entrega:</label>
                    <input class="form-control" required name="deliveryTime" id="deliveryTime" min="0" type="number" value="${deliveryTime}"/>
                </div>
                <div class="form-group">
                    <label class="control-label">* Cômodos Associados:</label>
                    <table id="comodosAssociados" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            JSONArray comodos = (JSONArray)request.getAttribute("Comodos");
                            for(int i = 0; i < comodos.length(); i++){
                        %>
                        <tr>
                            <td></td>
                            <td><%= comodos.getJSONObject(i).get("Id").toString() %></td>
                            <td><%= comodos.getJSONObject(i).get("Descrição").toString() %></td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                </div>
                <button class="btn btn-primary" type="submit" id="btnCriar">Alterar Mobília</button>
                <a class="btn btn-danger" href="/List/Mobilia">Cancelar</a>
            </div>
        </form>
    </div>
</div>
</body>
<script type="application/javascript">
    $(document).ready(function(){
        var selected = $("#hfComodosIds").val().split(";");
        debugger;
        var table = $("#comodosAssociados").DataTable({
            columnDefs: [ {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
            } ],
            select: {
                style:    'multi',
                selector: 'td:first-child'
            },
            order: [[ 1, 'asc' ]]
        } ).rows( function ( idx, data, node ) {
            return selected.indexOf(data[1]) >= 0;
        } ).select();

        $("#btnCriar").on("click", function(e){
            var selectedRows = table.rows( { selected: true } )[0];
            var ids = [];

            if(selectedRows.length < 1){
                toastr.error("Não foi possível criar a mobília, pois é necessário selecionar pelo menos 1 cômodo.");
                e.preventDefault();
                return;
            }

            for(var i = 0; i < selectedRows.length; i++){
                ids.push(table.row(selectedRows[i]).data()[1]);
            }

            $("#hfComodosIds").val(ids.join(";"));
        })
    });
</script>
</html>
