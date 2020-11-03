<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/resource/css/bootstrap.min.css" var="bootstrapCSS"></c:url>
<c:url value="/resource/js/bootstrap.min.js" var="bootstrapJS"></c:url>
<c:url value="/resource/js/jquery.min.js" var="jqueryJS"></c:url>

<link rel="stylesheet" href="${bootstrapCSS }" />
<script type="text/javascript" src="${jqueryJS }"></script>
<script type="text/javascript" src="${bootstrapJS }"></script>