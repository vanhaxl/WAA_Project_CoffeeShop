<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/resources/css/app.css">
<title>Boss Coffee</title>
</head>
<body>
	<h1>Welcome to Maharishi University of Management</h1>
	<br />
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="dropdown">
			<button class="dropbtn">Products</button>
			<div class="dropdown-content">
				<a href="/products">View All Products</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">Users</button>
			<div class="dropdown-content">
				<a href="/signup">Create Users</a>
			</div>
			<div class="dropdown-content">
				<a href="/persons">View All Users</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">Orders</button>
			<div class="dropdown-content">
				<a href="/orders">View All Oders</a>
			</div>
		</div>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<div class="dropdown">
			<button class="dropbtn">View Cart</button>
			<div class="dropdown-content">
				<a href="/orders">View Cart</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">Personal Information</button>
			<div class="dropdown-content">
				<a href="/persons/update">Update Info</a>
			</div>
		</div>
	</sec:authorize>

</body>
</html>