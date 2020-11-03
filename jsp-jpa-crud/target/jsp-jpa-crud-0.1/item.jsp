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
		
		
		<div class="row mt-2">
			<div class="col-10">
				<h3>All Item</h3>
			</div>	
		    <div class="col">
		    	 <c:url var="add" value="/item-add"></c:url>
				<a href="${add }" class="btn btn-success">Add New Item</a>
			</div> 
		</div>
		<table class="table col mt-2">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
					<th>Category Name</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<!-- Item List -->
				<c:forEach items="${items }" var="i">
					<tr>
					
						<td><c:set var="Idno" value="${Idno+1}"/>
							<c:out value="${Idno}"/>
						</td>
						<td>${i.name }</td>
						<td>${i.price }</td>
						<td>${i.category.name }</td>
						<td>
							<c:url var="edit" value="/item-edit">
								<c:param name="itemid">${i.id }</c:param>
							</c:url>
							<a href="${edit }" class="btn btn-outline-success">Edit</a>
						</td>
						<td class="text-center">
							<c:url var="delete" value="/item-delete">
								<c:param name="itemid">${i.id }</c:param>
							</c:url>
							<a href="${delete }" class="btn btn-outline-danger">Delete</a>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
	</div>

	</div>
</body>
</html>