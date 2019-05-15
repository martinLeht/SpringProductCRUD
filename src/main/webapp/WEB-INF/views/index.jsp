<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Products | DataPoint</title>
	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/icons/database.png"/>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery-slim.min.js"></script>
	
	<!-- Popper JS -->
	<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
</head>

<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	  	<h2><img src="${pageContext.request.contextPath}/resources/images/icons/databasewhite.png" width="40" height="40" class="d-inline-block align-top" alt=""> Product Management System</h2>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
			<span class="navbar-toggler-icon"></span>
	  	</button>
	
	  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
	    	<ul class="navbar-nav mx-auto">
	    	
    			<li class="nav-item active">
	        		<a class="nav-link" href="${pageContext.request.contextPath}/">View Products</a>
	      		</li>
	      		
	  		    
	  		    <!-- Display link in navigation only if user is manager or admin -->
	  		   	<sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
					<li class="nav-item active">
			        	<a class="nav-link" href="${pageContext.request.contextPath}/add">Add Product</a>
					</li>
				</sec:authorize>
		    </ul>
	    
			<!-- Search bar -->
			<form:form action="search" method="POST" cssClass="form-inline">
				<input type="text" name="searchName" placeholder="Search by name" class="form-control mr-sm-2" aria-label="Search" />
				
				<input class="btn btn-light my-sm-0" type="submit" value="Search"/>
			</form:form>
			
			<!-- Logout Button -->	    
		    <form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST" cssClass="form-inline">
				<input class="btn btn-light my-sm-0" type="submit" value="Logout" />
			</form:form>
			

  		</div>
	</nav>
	
	
	<div id="container">
		
		<div id="content">
			<h2 id="header" >Products</h2>
			
			<div id="separator"></div>
			
			<!-- product table -->
			
			<table class="table table-striped table-border table-hover" id="mydata">
				
				<thead class="thead-dark">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Product</th>
						<th scope="col">Price($/USD)</th>
						<th scope="col">Quantity</th>
						<th scope="col">Added By</th>
						<th scope="col">Options</th>
					</tr>
				</thead>
				<tbody class="tbody-light">
					<!-- loop and print products in products model -->
					<c:forEach var="product" items="${ products }">
						
						<!-- Create update and delete link if user is admin -->
						<sec:authorize access="hasRole('ADMIN')">
						
							<!-- update link with product id -->
							<c:url var="updateLink" value="/updateProduct">
								<c:param name="productId" value="${product.id}"/>
							</c:url>
							
							<!-- update link with product id -->
							<c:url var="deleteLink" value="/delete">
								<c:param name="productId" value="${product.id}"/>
							</c:url>
							
						</sec:authorize>
						
						<!-- view link with product id -->
						<c:url var="viewLink" value="/view/${product.id}" />
					
					
					
						<tr>
							<th scope="row">${ product.id }</th>
							<td>${ product.productName }</td>
							<td>${ product.price }</td>
							<td>${ product.quantity }</td>
							<td>${ product.addedBy }</td>
							
							<!-- Display links -->
							<td>
								<a href="${viewLink}">View</a>
								
								<!-- Display update and delete link only if user is admin -->
								<sec:authorize access="hasRole('ADMIN')">
									|
									<a href="${updateLink}">Update</a>
									|
									<a href="${deleteLink}"
									   onclick="if (!(confirm('Are you sure you want to delete this product?'))) return false">Delete</a>
								</sec:authorize>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small special-color-dark pt-4">	
	
	    <!-- Copyright -->
	    <div class="footer-copyright text-center py-3">© 2018 Copyright:
	      <a href="https://mdbootstrap.com/education/bootstrap/"> MDBootstrap.com</a>
	    </div>
	    
	    <div class="footer-copyright text-center py-3">
	    	Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> 
	    	from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> 
	    	is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a>
	    </div>
	    <!-- Copyright -->
	
	  </footer>
	  <!-- Footer -->
	
</body>
</html>