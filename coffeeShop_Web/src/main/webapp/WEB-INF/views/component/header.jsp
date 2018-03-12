<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/products">Boss Coffee</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <sec:authorize var="loggedIn" access="isAuthenticated()"/>
                <sec:authentication var="user" property="principal"/>

                <c:if test="${loggedIn}">
                    <li class="nav-item" id="liMyAccount">
                        <a class="nav-link welcome">Hello, ${user.username}</a>
                    </li>
                </c:if>
                <li class="nav-item active product">
                    <a class="nav-link" href="/products">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item shopping-cart">
                    <a class="nav-link" href="orders">Shopping Cart</a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item dropdown product-list product-management">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Management
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/products/add">Create New Product</a>
                            <a class="dropdown-item" href="/products/admin">Product List</a>
                            <a class="dropdown-item" href="/persons/signup">Create New Person</a>
                            <a class="dropdown-item" href="/persons">Person List</a>
                            <a class="dropdown-item" href="/orders/admin">View all order</a>
                        </div>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_USER')">
                    <li class="nav-item myaccount" id="liMyAccount">
                        <a class="nav-link" href="/persons/updateProfile/${user.username}">My Profile</a>
                    </li>
                </sec:authorize>

                <c:if test="${!loggedIn}">
                    <li class="nav-item login" id="liLogin">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                </c:if>

                <c:if test="${loggedIn}">
                    <li class="nav-item logout" id="liLogout">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>