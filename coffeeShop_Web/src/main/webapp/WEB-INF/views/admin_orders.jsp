<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Orders</title>
    <script src="/resources/js/jquery/jquery.min.js"></script>
    <script src="/resources/js/app.js"></script>
    <link rel="stylesheet" href="/resources/css/app.css">
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Orders</h1>
            <p>All the Orders in System</p>
        </div>
    </div>
</section>
<section class="container">
    <div>
        <table class="table table-hover">
            <tr>
                <th>Date</th>
                <th>Person</th>
            </tr>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.orderDate}</td>
                    <td>${order.person.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>

</body>
</html>
