<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="resource/common.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="resource/menu.jsp"></jsp:include>

		<div class="row">
			<h4 class="col-6 mt-2">${not empty category ? 'Edit Category' : 'Add New Category' }</h4>
		</div>
		<c:url value="/category-add" var="save"></c:url>
		<form action="${save }" class="form col-6" method="post">
		<input type="hidden" name="categoryid" value="${category.id}"/>
			<div class="form-group">
				<label for="">Category Name</label> 
				<input type="text" value="${category.name}" name="categoryname" class="form-control" required="required"
					placeholder="Enter Category Name" />
			</div>
			<button type="submit" class="btn btn-success">Save</button>
			<button type="reset" class="btn btn-danger">Clear</button>
		</form>
	</div>
</body>
</html>