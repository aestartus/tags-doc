<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tags de Documentos</title>

    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/4.1/examples/dashboard/dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">${pageContext.request.userPrincipal.name}</a>
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          </form>
          <a class="nav-link" href="#" onclick="document.forms['logoutForm'].submit()">Salir</a>
        </li>
      </ul>
    </nav>
</c:if>
    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="/">
                  <span data-feather="home"></span>
                  Inicio
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="/documents">
                  <span data-feather="file"></span>
                  Documentos<span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="users"></span>
                  Customers
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="bar-chart-2"></span>
                  Reportes
                </a>
              </li>
            </ul>

          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">Subir documento</h1>
          </div>
            <form id="documentForm" method="POST" enctype="multipart/form-data" action="${contextPath}/documents">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <div class="form-row">
                <div class="form-group col-md-6">
                  <label for="nameOfFile">Nombre del documento</label>
                  <input type="text" class="form-control" id="nameOfFile" name="nameOfFile" placeholder="Titulo del documento">
                </div>
                <div class="form-group col-md-6">
                  <label for="metaData">Tags</label>
                  <input type="text" class="form-control" id="metaData" name="metaData" placeholder="">
                </div>
              </div>
              <div class="form-group">
              <div class="custom-file form-group">
               <input type="file" class="custom-file-input" id="file" name="file">
               <label class="custom-file-label" for="file">Escoge un Archivo</label>
             </div>
             </div>
              <button type="submit" class="btn btn-primary">Subir</button>
            </form>


        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://getbootstrap.com/docs/4.1/assets/js/vendor/popper.min.js"></script>
    <script src="https://getbootstrap.com/docs/4.1/dist/js/bootstrap.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
      $custom-file-text: (
        en: "Browse",
        es: "Elegir"
      );
    </script>
<!-- /container -->

</body>
</html>
