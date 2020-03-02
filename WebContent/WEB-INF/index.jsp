<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOMBOOM GALLERY</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Photo Perfect Template built with Bootstrap Framework for creating Photo Gallary by TemplateFlip.com"/>
    <link href="https://fonts.googleapis.com/css?family=Arimo:400,600,700" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link rel="shortcut icon" type="image/png" href="images/favicon.png">
    <link href="./css/main.css" rel="stylesheet">
</head>
<body id="top">
    <div class="page">
      <%@ include file ="header.jsp" %>
      <div class="page-content">
        <div class="container">
<div class="container pp-section">
  <div class="row">
    <div class="col-md-9 col-sm-12 px-0">
      <h1 class="h3"> We are Photo Perfect, A Digital Photography Studio.</h1>
    </div>
  </div>
</div>
<div class="container px-0 py-4">
  <div class="pp-category-filter">
    <div class="row">
      <div class="col-sm-12">
      <c:choose>
      	<c:when test="${!empty sessionScope.username}">
	     <div class="container">
	      <a class="btn btn-primary" href="addalbum">+ ajouter un album</a>
	      <a class="btn btn-primary" href="addPhoto">+ ajouter une photo</a>
	     <div class="row">
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="people">People</a>
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="nature">Nature</a>
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="computer">Computer</a>
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="food">Food</a>
	      <c:forEach var="album" items="${albums}">
	      		<a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="food">${album.albumName}</a>	 
	      </c:forEach>
	     </div>
	     </div>
      	</c:when>
      	<c:otherwise>
      	<c:forEach var="album" items="${albums}">
	      	<c:if test="${album.shared}">
	      		<a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="food">${album.albumName}</a>
	      	</c:if> 	 
	      </c:forEach>
      	  <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="people">People</a>
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="nature">Nature</a>
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="computer">Computer</a>
	      <a class="btn btn-outline-primary pp-filter-button" href="#" data-filter="food">Food</a>
      	</c:otherwise>
      </c:choose>
      
      </div>
    </div>
  </div>
</div>
<div class="container px-0">
  <div class="pp-gallery">
    <div class="card-columns">
      <div class="card" data-groups="[&quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/1-nature.jpg" alt="Nature"/>
            <figcaption>
              <div class="h4">Forest</div>
              <p>Nature</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/2-nature.jpg" alt="Nature"/>
            <figcaption>
              <div class="h4">Bird</div>
              <p>Nature</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/4-nature.jpg" alt="Nature"/>
            <figcaption>
              <div class="h4">Sunrise</div>
              <p>Nature</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/5-nature.jpg" alt="Nature"/>
            <figcaption>
              <div class="h4">Greenery</div>
              <p>Nature</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/8-nature.jpg" alt="Nature"/>
            <figcaption>
              <div class="h4">Bird</div>
              <p>Nature</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/9-nature.jpg" alt="Nature"/>
            <figcaption>
              <div class="h4">Flower</div>
              <p>Nature</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;people&quot; , &quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/10-people.jpg" alt="People"/>
            <figcaption>
              <div class="h4">Model</div>
              <p>People</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;people&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/11-people.jpg" alt="People"/>
            <figcaption>
              <div class="h4">Cute</div>
              <p>People</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;people&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/13-people.jpg" alt="People"/>
            <figcaption>
              <div class="h4">Model</div>
              <p>People</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;people&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/14-people.jpg" alt="People"/>
            <figcaption>
              <div class="h4">Model</div>
              <p>People</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;people&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/16-people.jpg" alt="People"/>
            <figcaption>
              <div class="h4">Model</div>
              <p>People</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;people&quot; , &quot;nature&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/17-people.jpg" alt="People"/>
            <figcaption>
              <div class="h4">Model</div>
              <p>People</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;computer&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/18-computer.jpg" alt="Computer"/>
            <figcaption>
              <div class="h4">Laptop</div>
              <p>Computer</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;computer&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/19-computer.jpg" alt="Computer"/>
            <figcaption>
              <div class="h4">Laptop</div>
              <p>Computer</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;computer&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/20-computer.jpg" alt="Computer"/>
            <figcaption>
              <div class="h4">Laptop</div>
              <p>Computer</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;computer&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/23-computer.jpg" alt="Computer"/>
            <figcaption>
              <div class="h4">Laptop</div>
              <p>Computer</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;computer&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/24-computer.jpg" alt="Computer"/>
            <figcaption>
              <div class="h4">Laptop</div>
              <p>Computer</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;food&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/26-food.jpg" alt="Food"/>
            <figcaption>
              <div class="h4">Fruit Salad</div>
              <p>Food</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;food&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/27-food.jpg" alt="Food"/>
            <figcaption>
              <div class="h4">Oranges</div>
              <p>Food</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;food&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/28-food.jpg" alt="Food"/>
            <figcaption>
              <div class="h4">Lemon Tea</div>
              <p>Food</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;food&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/29-food.jpg" alt="Food"/>
            <figcaption>
              <div class="h4">Pasta</div>
              <p>Food</p>
            </figcaption>
          </figure></a></div>
      <div class="card" data-groups="[&quot;food&quot;]"><a href="image-detail.html">
          <figure class="pp-effect"><img class="img-fluid" src="images/30-food.jpg" alt="Food"/>
            <figcaption>
              <div class="h4">Burger</div>
              <p>Food</p>
            </figcaption>
          </figure></a></div>
    </div>
  </div>
</div>
<div class="pp-section"></div></div>
      </div>
      <footer class="pp-footer">
        <div class="container py-5">
          <div class="row text-center">
            <div class="col-md-12"><a class="pp-facebook btn btn-link" href="#"><i class="fa fa-facebook fa-2x " aria-hidden="true"></i></a><a class="pp-twitter btn btn-link " href="#"><i class="fa fa-twitter fa-2x " aria-hidden="true"></i></a><a class="pp-google-plus btn btn-link" href="#"><i class="fa fa-google-plus fa-2x" aria-hidden="true"></i></a><a class="pp-instagram btn btn-link" href="#"><i class="fa fa-instagram fa-2x " aria-hidden="true"></i></a></div>
            <div class="col-md-12">
              <p class="mt-3">Copyright &copy; Photo Perfect. All rights reserved.<br>Design - <a class="credit" href="https://templateflip.com" target="_blank">TemplateFlip</a></p>
            </div>
          </div>
        </div>
      </footer>
    </div>
   <%@ include file ="footer.jsp" %>
  </body>
</html>