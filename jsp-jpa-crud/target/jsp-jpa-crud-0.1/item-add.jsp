<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<jsp:include page="resource/common.jsp"></jsp:include>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
		<jsp:include page="resource/menu.jsp"></jsp:include>
		
		<div class="row">
			<h4 class="col-6">${not empty item ? 'Edit Item' : 'Add New Item' }</h4>
		</div>
		<c:url var="save" value="/item-add"></c:url>
		<form action="" class="form col-6" method="post">
		<input type="hidden" name="itemid" value="${item.id}" />
			<div class="form-group">
				<label for="">Category Name</label>
				<select name="categoryid" id="" class="form-control">
					<c:forEach items="${categories }" var="c">
						<option value="${c.id }" ${item.category.id == c.id ? 'selected' : '' }>${c.name }</option>
					</c:forEach>
				</select> 
			</div>
			<div class="form-group">
				<label for="">Item Name</label>
				<input type="text" value="${item.name }" name="itemname" class="form-control" required="required" placeholder="Enter Item Name" />
			</div>
			
			<div class="form-group">
				<label for="">Price</label>
				<input type="number" value="${item.price }" name="price" class="form-control" required="required" placeholder="Enter Price" />
			</div>
			
			<button type="submit" class="btn btn-success">Save</button>
			<button type="reset" class="btn btn-danger">Clear</button>
		</form>

	</div>
</body>
</html>