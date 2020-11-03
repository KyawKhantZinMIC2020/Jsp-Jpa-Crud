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
			<div class="col-4">
				<h3>All Categories</h3>
			</div>	
			<div class="col">
				<c:url value="/category-add.jsp" var="add"></c:url>
				<a href="${add }" class="btn btn-success">Add New Category </a>
			</div>
		</div>
		<table class="table col-6">
			<thead>
				<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Category Name</th>
					<th colspan="2" class="text-center">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<!-- Category List -->
				<c:forEach items="${categories }" var="c">
					<tr>
						<td class="text-center">
							<c:set var="Idno" value="${Idno+1}"/>
							<c:out value="${Idno}"/>
						</td>
						<td class="text-center">${c.name }</td>
						<td class="text-center">
							<c:url value="/category-edit" var="action">
								<c:param name="categoryId">${c.id }</c:param>
							</c:url>
							<a href="${action }" class="btn btn-outline-info">Edit</a>
						</td>
						
						<td class="text-center">
							<c:url value="/category-delete" var="delete">${c.id }
								<c:param name="categoryid">${c.id }</c:param>
							</c:url>
							<a href="${delete }" class="btn btn-outline-danger">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>