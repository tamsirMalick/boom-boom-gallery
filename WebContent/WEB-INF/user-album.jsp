<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes albums</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="shortcut icon" type="image/png" href="images/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file ="header.jsp" %>
<div id="acount">
        <div class="container">
            <div id="acount-row" class="row justify-content-center align-items-center">
                <div id="acount-column" class="col-md-9">
                    <div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-10">
						<h2>
							Mes <b>Albums</b>
						</h2>
					</div>
					<div class="col-sm-2">
						<a href="addalbum" class="btn btn-success"><i class="material-icons">&#xE147;</i> 
							<span>Ajouter un album</span>
						</a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="column1">AlbumID</th>
						<th>NOM ALBUM</th>
						<th>PARTAGER</th>
						<th>ACTIONS</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="album" items="${albums}">
							<c:if test="${album.user.userid == sessionScope.user.userid }">
								<tr>
									<td><c:out value="${album.albumId}" /></td>
									<td><c:out value="${album.albumName}" /></td>
									<c:choose>
										<c:when test="${album.shared == true }">
											<td>partagé</td>
										</c:when>
										<c:otherwise>
											<td>non partagé</td>
										</c:otherwise>
									</c:choose>								
									<td>
										<a href="updatealbum?albumId=${album.albumId}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a> 
										<a href="deletealbum?albumId=${album.albumId}" class="delete" onclick="return confirm('En êtes vous sûr de vouloir supprimé cet album ?')"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
									</td>
								</tr>
							</c:if>						
						</c:forEach>						
					</tr>

				</tbody>
			</table>
		</div>
	</div>

            </div>
        </div>
    </div>
</body>
</html>