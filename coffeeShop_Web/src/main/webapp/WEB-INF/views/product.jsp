<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Products</title>

</head>
<body>
<c:set var="baseURL" value="${pageContext.request.localName}"/>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Products</h1>
        </div>
    </div>
</section>
<section class="container" ng-app="cartApp">
    <div class="row">
        <div class="col-md-5">
            <img src="<c:url value="/img/${product.productName}.png"></c:url>"
                 alt="image" style="width: 100%"/>
        </div>

        <div class="col-md-5">
            <h3>${product.productName}</h3>
            <p>${product.description}</p>
            <p>
                <strong>Product Type</strong> : ${product.productType}
            </p>
            <h4><strong>Price</strong>: $ ${product.price}</h4>
            <p>
                <a href="<spring:url value="/products" />"
                   class="btn btn-default"> <span
                        class="glyphicon-hand-left glyphicon"></span> back
                </a> <a href="/orders/add/${product.id}" class="btn btn-warning btn-large"> <span
                    class="glyphicon-shopping-cart glyphicon"></span> Add to Cart
            </a> <a href="/orders" class="btn btn-default">
                <span class="glyphicon-hand-right glyphicon"></span> View Cart
            </a>
            </p>
        </div>
    </div>
</section>
</body>
</html>
