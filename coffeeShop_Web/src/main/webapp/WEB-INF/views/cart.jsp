<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

    <script src="/resources/js/jquery/jquery.min.js"></script>
    <script src="/resources/js/app.js"></script>
    <title>Cart</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Cart</h1>
            <p>All the selected products in your cart</p>
        </div>
    </div>
</section>

<section class="container">
    <div>

        <div>
            <button id="btnClearOrder" class="btn btn-danger pull-left"> <span class="glyphicon glyphicon-remove-sign"></span> Clear Cart</button>
            <a href="<spring:url value="/orders/checkout" />" class="btn btn-success pull-right">
                <span class="glyphicon-shopping-cart glyphicon"></span> Check out
            </a>
        </div>
        <table class="table table-hover">
            <tr>
                <th>Product</th>
                <th>Unit price</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${myOrder.orderLines}" var="orderLine">
                <tr>
                    <td>${orderLine.product.productName}</td>
                    <td>${orderLine.product.price}</td>
                    <td>${orderLine.quantity}</td>
                    <td>$${orderLine.cost}</td>
                    <td><a onclick='removeFromCart(${orderLine.product.id})' class="label label-danger"> <span class="glyphicon glyphicon-remove"/></span> Remove
                    </a></td>
                </tr>
            </c:forEach>
            <tr>
                <th></th>
                <th></th>
                <th>Grand Total</th>
                <th>$${myOrder.totalAmount}</th>
                <th></th>
            </tr>
        </table>

        <a href="<spring:url value="/products" />" class="btn btn-default">
            <span class="glyphicon-hand-left glyphicon"></span> Continue shopping
        </a>
    </div>
</section>
</body>
</html>
