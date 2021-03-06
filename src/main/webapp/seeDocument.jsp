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
        <script>
            function imgDecodeBase64(imagenBase64){
                var binaryImg = atob(imagenBase64);
                var length = binaryImg.length;
                var ab = new ArrayBuffer(length);
                var ua = new Uint8Array(ab);
                for (var i = 0; i < length; i++) {
                    ua[i] = binaryImg.charCodeAt(i);
                }
                var blob = new Blob([ab], {
                    type: "image/jpeg"
                });

                return  URL.createObjectURL(blob);
            }

            function showImage(id){
                var imagen = document.getElementById("pagina"+id).value;
                var blob = imgDecodeBase64(imagen);
                console.log(blob);
                $("#"+id).html();
                $("#"+id).html("<img class=\"img-fluid\" src=\""+blob+"\">");
            }
        </script>
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
                <a class="nav-link" href="/index">
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
            </ul>

          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">${document.nameOfFile}</h1>
          </div>
          <div class="container">
                <c:forEach var="page" items="${pages}">
                <div class="row">
                    <div class="col">
                        <input type="hidden" id="pagina${page.number}" value="${page.page}"/>
                        <div id="${page.number}"></div>
                        <script>
                            showImage(${page.number});
                        </script>
                    </div>
                    <div class="col">
                    </div>
                </c:forEach>
          </div>
        </main>
      </div>
    </div>


<!-- /container -->

</body>
</html>
