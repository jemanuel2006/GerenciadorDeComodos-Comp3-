<%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 22/09/2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="../www/Index.css">
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Gerenciador de Mobilias - COMP3</a>
        </div>
        <div class="collapse navbar-collapse js-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown mega-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<span class="caret"></span></a>
                    <ul class="dropdown-menu mega-dropdown-menu">
                        <li class="col-sm-4">
                            <ul>
                                <li class="dropdown-header">Gerenciar Cômodos</li>
                                <li><a href="/List/Cozinha">Cozinhas</a></li>
                                <li><a href="/List/Sala">Salas</a></li>
                                <li><a href="/List/Quarto">Quartos</a></li>
                                <li><a href="/List/ComodoComposto">Cômodos Compostos</a></li>
                            </ul>
                        </li>
                        <li class="col-sm-4">
                            <ul>
                                <li class="dropdown-header">Mobílias</li>
                                <li><a href="/List/Mobilia">Gerenciar Mobílias</a></li>
                            </ul>
                        </li>
                        <li class="col-sm-4">
                            <ul>
                                <li class="dropdown-header">Vendas</li>
                                <li><a href="#">Gerenciar Contratos</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.nav-collapse -->
    </nav>
</div>
<script type="application/javascript">
    $(document).ready(function(){
        <% if(request.getParameter("fixedMenu") == null || request.getParameter("fixedMenu").toString() == "false") {%>
        $(".dropdown").hover(
                function() {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
                    $(this).toggleClass('open');
                },
                function() {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
                    $(this).toggleClass('open');
                }
        );
        <%}%>
    });
</script>
