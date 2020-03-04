<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Image - Boom Boom</title>
<meta name="description" content="Your Image page description" />
<link href="https://fonts.googleapis.com/css?family=Arimo:400,600,700" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
<link rel="shortcut icon" type="image/png" href="images/favicon.png">
<link href="styles/main.css" rel="stylesheet">
</head>

<body id="top">
	<div class="page">
		<%@ include file="header.jsp"%>
		<div class="page-content">
			<div class="container">
				<div class="container pp-section">
					<div class="h3 font-weight-normal">Boom Boom Gallery Studio</div>
					<img class="img-fluid mt-4" src="images/${image.imagePath}" />
					<div class="row mt-5">
						<div class="col-md-6">
							<div class="h5">INFOS</div>
							<div class="mr-1 badge badge-primary">Auteur : ${image.user.username}</div>
							<div class="mr-1 badge badge-primary">Titre : ${image.title}</div>
							<div class="mr-1 badge badge-primary">Album : ${image.album.albumName}</div>
							<div class="mr-1 badge badge-primary">Ajouté le : ${image.created}</div>
							
							<c:if test="${!empty sessionScope.username && image.user.username == sessionScope.username }">
								<div class="pt-4">
									<a class="mr-1 badge badge-danger" href="delete?imageId=${image.imageID}" onclick="return confirm('En êtes vous sûr de vouloir supprimé cette photo ?')"><i class="fa fa-trash mr-2" aria-hidden="true"></i>Supprimer cette photo</a>
									<a class="mr-1 badge badge-info" href="update?imageId=${image.imageID}"><i class="fa fa-edit mr-2" aria-hidden="true"></i>Modifier cette photo</a>
								</div>
							</c:if>
							
						</div>
						<div class="col-md-5">
							<div class="h6">Photo description : ${image.description}</div>
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
						<p class="mt-3">Copyright &copy; Photo Perfect. All rights reserved.
						<br>Design<a class="credit" href="https://templateflip.com" target="_blank">TemplateFlip</a>
						</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<%@ include file="footer.jsp"%>
</body>

</html>