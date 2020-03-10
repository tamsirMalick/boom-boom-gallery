<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link href="./css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./css/style.css">
<script src="./css/bootstrap/js/bootstrap.min.js"></script>
<script src="./css/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
<%@ include file ="header.jsp" %>
<div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="login" method="post" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Connexion</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">Username:</label><br>
                                <input type="text" name="username" id="username" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Mot de passe:</label><br>
                                <input type="password" name="password" id="password" class="form-control" required>
                            </div>
                            <c:if test="${!empty requestScope.error}">
                            	<p>username ou mot de passe incorrect</p>
                            </c:if>
                            <div class="form-group">
                                <label for="remember-me" class="text-info"><span>Se souvenir de moi</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Connecter">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="createAcount" class="text-info">Créer un compte ?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>