<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="#">Home</a>
	<ul class="navbar-nav">
		<c:url value="/categories" var="category"></c:url>
		<c:url value="/items" var="item"></c:url>
		<li class="nav-item"><a class="nav-link" href="${category }">Category</a></li>
		<li class="nav-item"><a class="nav-link" href="${item }">Item</a></li>
	</ul>

</nav>