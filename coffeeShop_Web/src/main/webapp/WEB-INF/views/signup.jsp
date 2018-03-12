<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Sign up</title>
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
            <h1>Sign Up Form</h1>
            <p>Fill in the sign up form to create new user</p>
        </div>
    </div>
</section>
<section class="container">
    <form:form method="POST" modelAttribute="newPerson" class="form-horizontal">
        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <fieldset>
            <legend>Update person</legend>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="firstName">First Name</label>
                <div class="col-lg-10">
                    <form:input id="firstName" path="firstName" type="text"
                                class="form:input-large"/>
                    <form:errors path="firstName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="lastName">Last Name</label>
                <div class="col-lg-10">
                    <form:input id="lastName" path="lastName" type="text"
                                class="form:input-large"/>
                    <form:errors path="lastName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="email">Email</label>
                <div class="col-lg-10">
                    <form:input id="email" path="email" type="text"
                                class="form:input-large" />
                    <form:errors path="email" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="password">Password</label>
                <div class="col-lg-10">
                    <form:input id="password" path="password" type="password"
                                class="form:input-large"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="role">Role</label>
                <div class="col-lg-10">
                    <form:input id="role" path="role" type="text"
                                class="form:input-large"/>
                    <form:errors path="role" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="city">City</label>
                <div class="col-lg-10">
                    <form:input id="city" path="city" type="text"
                                class="form:input-large"/>
                    <form:errors path="city" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="country">Country</label>
                <div class="col-lg-10">
                    <form:input id="country" path="country" type="text"
                                class="form:input-large"/>
                    <form:errors path="country" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="state">State</label>
                <div class="col-lg-10">
                    <form:input id="state" path="state" type="text"
                                class="form:input-large"/>
                    <form:errors path="state" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="zipcode">Zip Code</label>
                <div class="col-lg-10">
                    <form:input id="zipcode" path="zipcode" type="text"
                                class="form:input-large"/>
                    <form:errors path="zipcode" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="phone">Phone</label>
                <div class="col-lg-10">
                    <form:input id="phone" path="phone" type="text"
                                class="form:input-large"/>
                    <form:errors path="phone" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="enable">Enable</label>
                <div class="col-lg-10">
                    <form:input id="enable" path="enable" type="text"
                                class="form:input-large"/>
                    <form:errors path="enable" cssClass="text-danger"/>
                </div>
            </div>


            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnCreatePerson" class="btn btn-primary"
                           value="Sign Up" />
                </div>
            </div>
        </fieldset>
    </form:form>
</section>
</body>
</html>
