<%--
  Created by IntelliJ IDEA.
  User: juane
  Date: 30/08/2016
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Gerenciador de Comodos</title>
    <link rel="stylesheet" type="text/css" href="../www/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../www/toastr/build/toastr.min.css">
    <script type="application/javascript" src="../www/jquery/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="../www/boostrap/js/bootstrap.min.js"></script>
  </head>
  <body>
  <jsp:include page="NavBarGerenciador.jsp" flush="true">
      <jsp:param name="fixedMenu" value="true" />
  </jsp:include>
  <script type="application/javascript">
    $(document).ready(function(){
        $('body').on({
            "click":function(e){
                e.stopPropagation();
            }
        });
        $('.dropdown').addClass('open');
    });
  </script>
  </body>
</html>
