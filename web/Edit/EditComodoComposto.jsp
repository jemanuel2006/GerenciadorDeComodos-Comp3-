<%@ page import="org.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 10/10/2016
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alterar Cômodo Composto</title>
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
    <legend>Alterar Cômodo Composto</legend>
    <div class="row">
        <form action="/Edit/ComodoComposto" method="post">
            <div class="col-md-8">
                <input name="hfId" id="hfId" type="hidden" value="${hfId}" />
                <input type="hidden" id="hfComodosIds" name="hfComodosIds" value="${hfComodosIds}">
                <div class="form-group">
                    <label class="control-label" for="description">* Descrição:</label>
                    <input class="form-control" required name="description" id="description" type="text" value="${description}"/>
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
                <button class="btn btn-primary" type="submit" id="btnCriar">Alterar Cômodo Composto</button>
                <a class="btn btn-danger" href="/List/ComodoComposto">Cancelar</a>
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

            for(var i = 0; i < selectedRows.length; i++){
                ids.push(table.row(selectedRows[i]).data()[1]);
            }

            $("#hfComodosIds").val(ids.join(";"));
        })
    });
</script>
</html>
