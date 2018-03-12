<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Products</title>
    <script src="/resources/js/jquery/jquery.min.js"></script>
    <script src="/resources/js/app.js"></script>
    <link rel="stylesheet" href="/resources/css/app.css">
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Products</h1>
            <p>All the products in System</p>
        </div>
    </div>
</section>
<section class="container">
    <div>
        <table class="table table-hover">
            <tr>
                <th>Image</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Product Type</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <th scope="row"><img class="product-image" src="<c:url value="/img/${product.productName}.png"></c:url>" alt="image" /></th>
                    <td>${product.productName}</td>
                    <td>${product.price}</td>
                    <td>${product.productType}</td>
                    <td>
                        <a href="<spring:url value="/products/update/${product.id}" />" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil"></span> Edit
                        </a>
                        <button onclick='removeProduct(${product.id})'> <span class="glyphicon glyphicon-trash"/></span> Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>

</body>
</html>
