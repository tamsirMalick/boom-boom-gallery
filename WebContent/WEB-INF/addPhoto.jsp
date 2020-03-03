<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter une photo</title>
<link href="./css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./css/style.css">
<script src="./css/bootstrap/js/bootstrap.min.js"></script>
<script src="./css/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
<%@ include file ="header.jsp" %>
<div id="acount">
        <div class="container">
            <div id="acount-row" class="row justify-content-center align-items-center">
                <div id="acount-column" class="col-md-6">
                    <div id="acount-box" class="col-md-12">
                        <form id="acount-form" class="form" action="addPhoto" method="post" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Nouvelle photo</h3>
                            <div class="form-group">
                                <label for="title" class="text-info">Titre:</label><br>
                                <input type="text" name="title" id="title" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="description" class="text-info">Description:</label><br>
                                <input type="text" name="description" id="description" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="higth" class="text-info">Hauteur:</label><br>
                                <input type="number" name="heigth" id="heigth" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="width" class="text-info">Largeur:</label><br>
                                <input type="number" name="width" id="width" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="photo" class="text-info">Selectioner une photo:</label><br>
                                <input type="file" name="photo" id="photo" class="form-control">
                            </div>
                            
                            <div class="form-group">
                                <label for="album" class="text-info">Selectioner une album:</label><br>
                                <select class="form-control" name="album" id="album">
                                <c:forEach var="album" items="${albums}">
                                	<option value="${album.albumId}">${album.albumName}</option>
                                </c:forEach>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Valider">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>