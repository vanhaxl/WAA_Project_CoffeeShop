<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Persons</title>
    <script src="/resources/js/jquery/jquery.min.js"></script>
    <script src="/resources/js/app.js"></script>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Persons</h1>
            <p>All the persons in System</p>
        </div>
    </div>
</section>
<section class="container">
    <div>
        <table class="table table-hover">
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>Enable</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${persons}" var="person">
                <tr>
                    <td>${person.email}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td>${person.address.zipcode}</td>
                    <td>${person.enable}</td>
                    <td>
                        <a href="<spring:url value="/persons/update/${person.id}" />" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil"></span> Edit
                        </a>
                        <button onclick='removePerson(${person.id})'> <span class="glyphicon glyphicon-trash"/></span> Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>

</body>
</html>
