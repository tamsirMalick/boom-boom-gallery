<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
							Gesiton <b>Utilisateurs</b>
						</h2>
					</div>
					<div class="col-sm-2">
						<a href="createAcount" class="btn btn-success"><i class="material-icons">&#xE147;</i> 
							<span>Ajouter un utilisateur</span>
						</a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="column1">UserID</th>
						<th>USERNAME</th>
						<th>EMAIL</th>
						<th>ROLE</th>
						<th>PASSWORD</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="user" items="${users}">
							<tr>
								<td><c:out value="${user.userid}" /></td>
								<td><c:out value="${user.username}" /></td>
								<td><c:out value="${user.email}" /></td>
								<td><c:out value="${user.role}" /></td>
								<td><c:out value="${user.password}"/></td>
								<td>
									<a href="updateuser?userId=${user.userid}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a> 
									<a href="deleteuser?userId=${user.userid}" class="delete" onclick="return confirm('En êtes vous sûr de vouloir supprimé cet utilisateur ?')"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
								</td>
							</tr>
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