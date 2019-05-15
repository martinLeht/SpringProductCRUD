<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Product | DataPoint</title>
	
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
	
	
	<div class="col-lg-9 col-lg-offset-9" id="container">
		<div id="content">
		
			<h3 id="header" >Save Product</h3>
			
			<div id="separator"></div>
		
			<form:form action="save" modelAttribute="product" method="POST">
				
				<!-- associate data with product id -->
				<form:hidden path="id" />
				
				<form:input type="datetime" path="createDateTime"/>
				
				<div class="form-group row">
				    <label for="nameInput" class="col-sm-2 col-form-label">Product name *:</label>
				    <div class="col-sm-10">
					    <form:input path="productName" cssClass="form-control" id="nameInput" placeholder="Enter name" />
					    <form:errors path="productName" cssClass="errors" />
			    	</div>
			  	</div>
			  	
			  	<div class="form-group row">
				    <label for="priceInput" class="col-sm-2 col-form-label">Price($) *:</label>
				    <div class="col-sm-10">
					    <form:input path="price" cssClass="form-control" id="priceInput" placeholder="Enter price" />
					    <form:errors path="price" cssClass="errors" />
			    	</div>
			  	</div>
			  	
			  	<div class="form-group row">
				    <label for="quantityInput" class="col-sm-2 col-form-label">Quantity *:</label>
				    <div class="col-sm-10">
					    <form:input path="quantity" cssClass="form-control" id="quantityInput" placeholder="Enter qty" />
					    <form:errors path="quantity" cssClass="errors" />
			    	</div>
			  	</div>
			  	
			 	  	
			  	<div class="form-group row">
				    <label for="emailInput" class="col-sm-2 col-form-label">Added by(email) *:</label>
				    <div class="col-sm-10">
					    <form:input path="addedBy" cssClass="form-control" id="emailInput" placeholder="example.address@email.com" />
					    <form:errors path="addedBy" cssClass="errors"  />
			    	</div>
			  	</div>
			  	
			  	<div id="separator" > </div>
			  	
			  	
			  	<h5 id="header" >Product Details (Can be updated later)</h5>
				 
				<form:hidden path="productDetail.id" />
				 
			  	<div class="form-group row">
			  		<label for="categoryInput" class="col-sm-2 col-form-label">Category *:</label>
			  		<div class="col-sm-3">
			  			<form:select path="productDetail.category" id="categoryInput" cssClass="form-control">
			  				<form:option value="" label="Select Product Category" />
			  				<form:options items="${categoryMap}"/>
			  			</form:select>
			  			<form:errors path="productDetail.category" cssClass="errors"  />
		  			</div>
			  	</div>
			  	
			  	<div class="form-group row">
				    <label for="manufacturerInput" class="col-sm-2 col-form-label">Manufacturer *:</label>
				    <div class="col-sm-4">
					    <form:input path="productDetail.manufacturer" cssClass="form-control" id="manufacturerInput" placeholder="Enter manufacturer" />
					    <form:errors path="productDetail.manufacturer" cssClass="errors" />
			    	</div>
			    	
			    	<label for="madeInInput" class="col-sm-2 col-form-label">Country *:</label>
			    	<div class="col-sm-3">
					    <form:select path="productDetail.countryMadeIn" id="madeInInput" cssClass="form-control">
			  				<form:option value="" label="Country manufactured in" />
			  				<form:options items="${countryMap}"/>
			  			</form:select>
			  			<form:errors path="productDetail.countryMadeIn" cssClass="errors"  />
			    	</div>
			  	</div>
			  	
			  	
			  	<div class="form-group row">
				    <label for="descriptionInput" class="col-sm-2 col-form-label">Description *:</label>
				    <div class="col-sm-10">
					    <form:textarea path="productDetail.description" cssClass="form-control" id="descriptionInput" placeholder="Short description of product..." />
					    <form:errors path="productDetail.description" cssClass="errors"  />
			    	</div>
			  	</div>
			  	
			  	
			  	<div class="form-group row">
				    <label for="weightInput" class="col-sm-2 col-form-label">Weight(g):</label>
				    <div class="col-sm-10">
					    <form:input path="productDetail.weight" cssClass="form-control" id="weightInput" placeholder="Enter weight" />
			    	</div>
			  	</div>
				  	
			  	<input type="submit" value="Add" class="btn btn-primary" />
			
			</form:form>
			
			<div style="clear; both;"></div>
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