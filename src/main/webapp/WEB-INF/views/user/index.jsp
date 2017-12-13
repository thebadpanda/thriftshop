<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>


<%-- <sec:authorize access="isAuthenticated()"> --%>
<%-- 	<sec:authorize access="hasRole('ROLE_ADMIN')"> --%>
<!-- 		<a href="/admin">admin</a> -->
<%-- 	</sec:authorize> --%>
<%-- 	<form:form action="/logout" method="POST"> --%>
<!-- 		<button type="submit" class="btn btn-danger">Logout</button> -->
<%-- 	</form:form> --%>
<%-- </sec:authorize> --%>
<%-- <sec:authorize access="!isAuthenticated()"> --%>
<!-- 	<a href="/login">Login</a> -->
<!-- 	<a href="/registration">Registration</a> -->
<%-- </sec:authorize> --%>

<div class="row">
    <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav" >
            <li><a href="/admin/">Admin</a></li>
            <li><a href="/admin/login"> Login</a> </li>
        </ul>
    </div>
    <div class="col-md-2 col-xs-12">
        <form:form class="form-horizontal" action="/" method="GET" modelAttribute="filter">
            <custom:hiddenInputs excludeParams="min, max, search, colorIds, sizeIds, subcategoryIds"/>
            <div class="form-group">
                <div class="col-sm-6">
                    <form:input path="min" class="form-control" placeholder="Min"/>
                </div>
                <div class="col-sm-6">
                    <form:input path="max" class="form-control" placeholder="Max"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-8">
                    <form:input path="search" class="form-control" placeholder="search by name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="contol-label col-sm-12">Color</label>
                <div class="col-sm-12">
                    <form:checkboxes element="div" items="${color}" itemValue="id" itemLabel="name" path="colorIds"/>
                </div>
            </div>
            <div class="form-group">
                <label class="contol-label col-sm-12">Size</label>
                <div class="col-sm-12">
                    <form:checkboxes element="div" items="${size}" itemValue="id" itemLabel="name" path="sizeIds"/>
                </div>
            </div>
            <div class="form-group">
                <label class="contol-label col-sm-12">Subcategory</label>
                <div class="col-sm-12">
                    <form:checkboxes element="div" items="${subcategory}" itemValue="id" itemLabel="name" path="subcategoryIds"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Ok</button>
            <a href  = "/cancelF" class="btn btn-primary" >Cancel</a>
        </form:form>
    </div>


    <div class="item">
        <
        <style>
            .item
            {-moz-column-count: 2;
                -webkit-column-count: 2;
                column-count: 2;
            }
        </style>

        <div class="col-sm-8 col-xs-12 parent" >
        <h1> List of products:</h1>

        <c:forEach items="${page.content}" var="item" >
            <div>
                <custom:hiddenInputs excludeParams="name, price, size, subcategory"/>
                <%--<img src="/images/item/${item.id}.jpg?version=${item.version}" width="50%">--%>

                <p>Name: ${item.name}</p>
                <p>Price: ${item.price} $USD</p>
                <p>Color: ${item.color.name}</p>
                <p>Size: ${item.size.name}</p>
                <p>Category: ${item.subcategory.name}</p>
                <%--<p>${item.category.name}</p>--%>
                <%--<c:forEach items="${digitalUnits}" var="digitalUnit">--%>
                    <%--<p>${item.digitalUnit.name}</p>--%>
                <%--</c:forEach>--%>
                    <%-- 				<c:forEach items="${item.featureStrings}" var="featureString"> --%>
                    <%-- 				<p>${item.featureStrings}</p> --%>
                    <%-- 				</c:forEach> --%>
                <a href="/item/${item.id}" class="btn btn-primary">${item.name}</a>
                <%--<sec:authorize access="isAuthenticated()">--%>
                    <a href="/buy/${item.id}" class="btn btn-primary">В корзину!</a>
                <%--</sec:authorize>--%>
            </div>
        </c:forEach>

        </div>
    </div>
    <div class="col-md-2 col-xs-12">
        <div class="row">
            <div class="col-md-6 col-xs-6 text-center">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button"
                            data-toggle="dropdown">
                        Sort <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <custom:sort innerHtml="Name asc" paramValue="name" />
                        <custom:sort innerHtml="Name desc" paramValue="name,desc" />
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-xs-6 text-center">
                <custom:size posibleSizes="5,10,25,50" size="${page.size}" />
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
    </div>
</div>
<script>
//    $('label').each(function() {
//        if(!$(this).html()) $(this).parent('div').hide();
//    });
//    <script>
    $('label').each(function() {
        if(!$(this).html()) $(this).parent('div').hide();
    });
</script>
<script type="text/javascript">
    $("#myCarousel").carousel();
</script>