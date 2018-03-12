<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Update Product</title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en">English</a>|<a href="?language=vi">Vietnamese</a>
			<a href="<c:url value="/logout" />">Logout</a>
		</div>
	</section>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
				<p>Update product</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form method="POST" modelAttribute="product" class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div"/>
			<fieldset>
				<legend>Update product</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="productName">Product Name</label>
					<div class="col-lg-10">
						<form:input id="productName" path="productName" type="text"
							class="form:input-large" value="${product.productName}"/>
						<form:errors path="productName" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="description">Description</label>
					<div class="col-lg-10">
						<form:input id="description" path="description" type="text"
									class="form:input-large" value="${product.description}"/>
						<form:errors path="description" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="price">Price</label>
					<div class="col-lg-10">
						<form:input id="price" path="price" type="text"
									class="form:input-large" value="${product.price}"/>
						<form:errors path="price" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="productType">Product Type</label>
					<div class="col-lg-10">
						<form:input id="productType" path="productType" type="password"
									class="form:input-large" value="${product.productType}"/>
						<form:errors path="productType" cssClass="text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="productImage"> Product Image
					</label>
					<div class="col-lg-10">
						<form:input id="productImage" path="productImage" type="file"
									class="form:input-large" />
					</div>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnUpdate" class="btn btn-primary"
							value="Update" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>
