<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>View | DataPoint</title>
	
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
	  	<h2>Product Management System</h2>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
			<span class="navbar-toggler-icon"></span>
	  	</button>
	
	  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
	    	<ul class="navbar-nav mx-auto">
	    	
    			<li class="nav-item active">
	        		<a class="nav-link" href="${pageContext.request.contextPath}/">View Products</a>
	      		</li>
	  		    
	  		    
	  		    <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
					<li class="nav-item active">
			        	<a class="nav-link" href="${pageContext.request.contextPath}/add">Add Product</a>
					</li>
				</sec:authorize>
		    </ul>	    
		    
		    
	    
			<!-- Search bar -->
			<form:form action="${pageContext.request.contextPath}/search" method="POST" cssClass="form-inline">
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

	<div class="container" id="container">
		<div id="content">
			<div class="col-lg-9">
	
				<div class="card mt-4">
					<img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="Picture of product">
					<div class="card-body">
						<h3 class="card-title">${product.productName}</h3>
						<h4>$${product.price}</h4>
						<p class="card-text">Description:</p>
						<p class="card-text">${product.productDetail.description}</p>
					</div>
				</div>
	          <!-- /.card -->
	          
	          	<!-- Update link declared to a var -->
				<c:url var="updateLink" value="/edit/${ product.id }" />
				<c:url var="updateGeneralLink" value="/edit/${ product.id }/general" />
				<c:url var="updateDetailsLink" value="/edit/${ product.id }/details" />
	
				<div class="card card-outline-secondary my-4">
					<div class="card-header">
					  Product Details 
					  <sec:authorize access="hasRole('ADMIN')">
					  	<a href="${ updateLink }">Edit</a>
					  	|
					  	<a href="${ updateGeneralLink }">Edit General</a>
					  	|
					  	<a href="${ updateDetailsLink }">Edit Details</a>
					  </sec:authorize>
					</div>
					<div>
					    <table class="table">
					        <tbody>
					        	<tr>
					                <td class="success">Product id: </td>
					                <td>${product.id}</td>
					            </tr>
					        	<tr>
					                <td class="success">Category: </td>
					                <td>${product.productDetail.category}</td>
					            </tr>
					            <tr>
					                <td class="success">Manufacturer: </td>
					                <td>${product.productDetail.manufacturer}</td>
					            </tr>
					            <tr>
					                <td class="success">Made in: </td>
					                <td>${product.productDetail.countryMadeIn}</td>
					            </tr>
					            <tr>
					                <td class="success">Weight: </td>
					                <td>${product.productDetail.weight}g</td>
					            </tr>
					            <tr>
					                <td class="success">Quantity: </td>
					                <td>${product.quantity} in stock</td>
					            </tr>
					            <tr>
					                <td class="success">Value in stock: </td>
					                <td>${product.valueInStock}$</td>
					            </tr>
					            <tr>
					                <td class="success">Creation date: </td>
					                <td>${product.createDateTime}</td>
					            </tr>
					            <tr>
					                <td class="success">Last updated date: </td>
					                <td>${product.updateDateTime}</td>
					            </tr>
					        </tbody>
					    </table>
					</div>
				</div>
			</div>
			<div id="separator"></div>
			<p>
				<a href="${pageContext.request.contextPath}/">Back to products</a>
			</p>
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