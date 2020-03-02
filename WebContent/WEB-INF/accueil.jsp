<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des inscriptions</title>
</head>
<body>
<%@ include file ="header.jsp" %>
	<h1>Gestion des utilisateurs</h1><hr>
	<form method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Ajout d'un participant</legend>
			<label>Username</label>
			<input type="text" name="username"><br/>
						<p></p>
			<label>Email</label>
			<input type="text" name="email"><br/>
						<p></p>
			<label>Role</label>
			<input type="text" name="role"><br/>
						<p></p>
			<label>Mot de passe</label>
			<input type="text" name="password"><br/>
						<p></p>
			<label>Selectionner une image</label>
			<input type="file" name="photo" size="50"><br/>
			<input type="submit" value="Ajouter">
		</fieldset>
	</form><hr>
	<c:choose>
		<c:when test="${!empty requestScope.users }">
			<c:forEach var="user" items="${ requestScope.users }">
				<c:out value="${user}"/><br>
				
			</c:forEach>
		</c:when>
		<c:otherwise>Aucun utilisateur n'a pour le moment été enregistré !</c:otherwise>
	</c:choose>
</body>
</html>