<%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 21/09/2016
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quartos Disponíveis</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../www/jsgrid-1.5.2/dist/jsgrid.min.css">
    <link rel="stylesheet" type="text/css" href="../www/jsgrid-1.5.2/dist/jsgrid-theme.min.css">
    <link rel="stylesheet" type="text/css" href="../www/sweetalert-master/dist/sweetalert.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/jsgrid-1.5.2/dist/jsgrid.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="../www/sweetalert-master/dist/sweetalert.min.js"></script>
</head>
<body>
    <jsp:include page="../NavBarGerenciador.jsp"></jsp:include>
    <div class="container">
        <legend>Quartos Disponíveis</legend>
        <button class="btn btn-success" id="btnCreate"><span class="glyphicon glyphicon-plus"></span> Criar Quarto</button>
        <div id="grid"  style="padding-top: 20px">

        </div>
    </div>
    <script type="application/javascript">
        $("#btnCreate").on("click", function(){
            window.location.href = "${basePath}" + "/Create/Quarto";
        });
        var grid = $("#grid").jsGrid({
            width: "100%",
            height: "400px",

            sorting: true,
            paging: true,
            confirmDeleting: false,

            data: ${list},

            fields: [
                {
                    type: "control",
                    editButton: true,
                    width: 35,
                    deleteButton: false,
                    itemTemplate: function(value, item) {
                        return $("<button class='jsgrid-button jsgrid-edit-button'>")
                                .on("click", function () {
                                    window.location.href = "${basePath}" + "/Edit/Quarto?id=" + item.Id;
                                });
                    }
                },
                { name: "Id", type: "number", width: 35 },
                { name: "Descrição", type: "text", width: 225, validate: "required" },
                {
                    type: "control",
                    width: 35,
                    editButton: false,
                    deleteButton: true,
                    itemTemplate: function(value, item) {
                        return $("<button class='jsgrid-button jsgrid-delete-button'>")
                                .on("click", function () {
                                    swal({
                                        title: "Deseja apagar o quarto '" + item.Descrição + "'?",
                                        text: "Não será possível recuperar os dados após a operação.",
                                        type: "warning",   showCancelButton: true,
                                        confirmButtonColor: "#DD6B55",
                                        confirmButtonText: "Apagar",
                                        cancelButtonText: "Cancelar",
                                        closeOnConfirm: false,   closeOnCancel: true
                                    }, function(isConfirm){
                                        if (isConfirm) {
                                            $.ajax({
                                                type: "DELETE",
                                                url: "/Delete/Quarto?id=" + item.Id
                                            })
                                            .done(function(data) {
                                                swal("Quarto apagado!", "O quarto ''" + item.Descrição + "' foi apagada.", "success");
                                                $("#grid").jsGrid("deleteItem", item);
                                            })
                                            .fail(function() {
                                                swal("Erro", "Ocorreu um erro ao apagar o quarto ''" + item.Descrição +
                                                        "'. Verifique se ele possui contratos ou mobilias associadas e tente novamente.", "error");
                                            });
                                        }
                                    });
                                });
                    }
                }
            ]
        })
    </script>
    </body>
</html>
