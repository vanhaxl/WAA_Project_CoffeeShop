<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Products</title>
    <script src="/resources/js/jquery/jquery.min.js"></script>
    <%--<script src="/resources/js/app.js"></script>--%>
    <link href="/resources/js/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/shop-homepage.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/product-list.css">
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="component/header.jsp"></jsp:include>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Products</h1>
            <p>All the available products in our store</p>
        </div>
    </div>
</section>

<section class="container">
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <img src="<c:url value="/img/${product.productName}.png"></c:url>" alt="image"  style = "width:100%"/>
                    <div class="caption">
                        <h3>${product.productName}</h3>
                        <p>${product.description}</p>
                        <p>$${product.price}</p>
                        <p>${product.productType}</p>
                        <p>
                            <a
                                    href=" <spring:url value="/products/productDetail?id=${product.id}" /> "
                                    class="btn btn-primary"> <span
                                    class="glyphicon-info-sign glyphicon" /></span> Details
                            </a>
                        </p>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
<jsp:include page="component/footer.jsp"></jsp:include>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
</body>
</html>
