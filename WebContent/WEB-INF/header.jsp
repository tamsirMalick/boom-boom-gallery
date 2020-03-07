<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Photo Perfect Template built with Bootstrap Framework for creating Photo Gallary by TemplateFlip.com"/>
    <link href="https://fonts.googleapis.com/css?family=Arimo:400,600,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link rel="shortcut icon" type="image/png" href="images/favicon.png">
    <link href="./css/main.css" rel="stylesheet">
</head>
<body>
<header>
        <div class="pp-header">
          <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container"><a href="index.html"><img src="images/favicon.png"></a><a class="navbar-brand" href="index">Boom-Boom-Gallery</a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
              <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <ul class="navbar-nav ml-auto">
                  <li class="nav-item active"><a class="nav-link" href="index">Accueil</a>
                  </li>
                  <li class="nav-item dist-c"><a class="nav-link" href="contact">Contact</a>
                  </li>
                  <c:choose>
                  	<c:when test="${!empty sessionScope.username }">
                  		<li class="nav-item"><a class="nav-link" href="deconnection">Se déconnecter</a></li>
                  		<li class="nav-item ml-3" style="text-transform: capitalize"><a href="#"><b>${fn:toLowerCase(sessionScope.username)}</b></a></li>
                  	</c:when>
                  	<c:otherwise>
                  	 	<li class="nav-item"><a class="nav-link" href="login">S'identifier</a></li>
                  		<li class="nav-item"><a class="nav-link" href="createAcount">S'inscrire</a></li> 
                  	</c:otherwise>
                  </c:choose>  
	              </ul>
              </div>
            </div>
          </nav>
        </div>
      </header>
</body>
</html>