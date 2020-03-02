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
                        <form id="acount-form" class="form" action="gallery" method="post" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Inscription</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">Username:</label><br>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                            
                            <div class="form-group">
                                <label for="username" class="text-info">Email:</label><br>
                                <input type="email" name="email" id="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Mot de passe:</label><br>
                                <input type="password" name="password1" id="password2" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Confirmer le mot de passe:</label><br>
                                <input type="password" name="password2" id="password2" class="form-control">
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