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
<meta name="description" content="Photo Perfect Template built with Bootstrap Framework for creating Photo Gallary by TemplateFlip.com" />
<link href="https://fonts.googleapis.com/css?family=Arimo:400,600,700" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
<link rel="shortcut icon" type="image/png" href="images/favicon.png">
<link href="./css/main.css" rel="stylesheet">
</head>
<body id="top">
	<div class="page">
		<%@ include file="header.jsp"%>
		<div class="page-content">
			<div class="container">
				<div class="container pp-section">
					<div class="row">
						<div class="col-md-9 col-sm-12 col-lg-12 px-0">
							<h1 class="h3">BOOM-BOOM GALLERY, ici vous pouvez ajoutez et partager tous vos photos</h1>
						</div>
					</div>
				</div>
				<div class="container px-0 py-4">
					<div class="pp-category-filter">
						<div class="row">
							<div class="col-sm-12">
								<c:choose>
									<c:when test="${!empty sessionScope.username}">
									<!-- Ces vus ne s'affichent que si l'utilisateur est authentifier -->
										<div class="container">
											<a class="btn btn-primary" href="addalbum">+ ajouter un album</a> 
											<a class="btn btn-primary" href="addPhoto">+ ajouter une photo</a>
											<a class="btn btn-primary" href="user-album">mes albums</a> 
											<div class="row">
												<a class="btn btn-outline-primary" href="gallery">Tous les photos</a>
												<c:forEach var="album" items="${albums}">
													<a class="btn btn-outline-primary" href="gallery?album=${album.albumName}">${album.albumName}</a>
												</c:forEach>
											</div>
										</div>
									</c:when>
									<c:otherwise>
									<!-- Si l'utilisateur n'est pas authetifier, je lui affiche seulement les albums qui sont partagés -->
										<a class="btn btn-outline-primary" href="gallery">Tous les photos</a>
										<c:forEach var="album" items="${albums}">			
											<c:if test="${album.shared}">
												<a class="btn btn-outline-primary" href="gallery?album=${album.albumName}">${album.albumName}</a>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>
				</div>
				<div class="container px-0">
					<div class="pp-gallery ">
						<div class="card-columns">
							<c:choose>
								<c:when test="${!empty requestScope.albumName}">
								<!-- Si l'utilisateur click sur un album, affcher seulement les images qui sont dans cet album -->
									<c:forEach var="image" items="${images}">		
										<c:if test="${requestScope.albumName == image.album.albumName}">
											<div class="card" data-groups="[&quot;${image.album.albumName }&quot;]">
												<a href="image-detail?imageId=${image.imageID}">
													<figure class="pp-effect">
														<img class="img-fluid" src="images/${image.imagePath}"alt="${image.title}" />
														<figcaption>
															<div class="h4">${image.album.albumName}</div>
															<p>Publié par : ${image.album.user.username}</p>
														</figcaption>									
													</figure>							
												</a>				
											</div>
										</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
								<!-- Sino l'utilisateur voit tous les images partagés de tous les utilisateur -->
									<c:forEach var="image" items="${images}">
										<c:if test="${image.album.shared}">
											<div class="card" data-groups="[&quot;${image.album.albumName }&quot;]">
												<a href="image-detail?imageId=${image.imageID}">
													<figure class="pp-effect">
														<img class="img-fluid" src="images/${image.imagePath}" alt="${image.title}" />
														<figcaption>
															<div class="h4">${image.album.albumName}</div>
															<p>Publié par : ${image.album.user.username}</p>
														</figcaption>
													</figure>
												</a>
											</div>
										</c:if>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="pp-section"></div>
			</div>
		</div>
		<footer class="pp-footer">
			<div class="container py-5">
				<div class="row text-center">
					<div class="col-md-12">
						<a class="pp-facebook btn btn-link" href="#"><i class="fa fa-facebook fa-2x " aria-hidden="true"></i></a>
						<a class="pp-twitter btn btn-link " href="#"><i class="fa fa-twitter fa-2x " aria-hidden="true"></i></a>
						<a class="pp-google-plus btn btn-link" href="#"><i class="fa fa-google-plus fa-2x" aria-hidden="true"></i></a>
						<a class="pp-instagram btn btn-link" href="#"><i class="fa fa-instagram fa-2x " aria-hidden="true"></i></a>
					</div>
					<div class="col-md-12">
						<p class="mt-3">Copyright &copy; BOOM-BOOM-GALLERY. All rights reserved.</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>