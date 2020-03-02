<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte</title>
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
                        <form id="acount-form" class="form" action="addalbum" method="post" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Ajouter un nouveau album </h3>
                            <div class="form-group">
                                <label for="albunName" class="text-info">Nom de l'album:</label><br>
                                <input type="text" name="albumName" id="albumName" class="form-control">
                               
                            </div>
							<div class="checkbox">
                             	<label><input type="checkbox" name="shared" id="shared" value="" class="form-control">Partager</label>
							</div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Ajouter">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>