<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="/admin/category">Category</a></li>
                    <li><a href="/admin/subcategory">Subcategory</a></li>
                    <li><a href="/admin/size">Size</a></li>
                    <li><a href="/admin/color">Color</a></li>
                    <li><a href="/admin/basket">Basket</a></li>
                    <li><a href="/admin/user">User</a></li>
                    <li class="active"><a href="/admin/item">Item</a><span
                            class="sr-only">(current)</span></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="row">
    <div class="col-md-3 col-xs-12"></div>
    <div class="col-md-7 col-xs-12">
        <c:if test="${subcategory ne null}">
            <div class="row">
                <div class="col-md-12 col-xs-12">
                    <form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item">
                        <form:hidden value="${subcategory.id}" path="subcategory"/>
                        <div class="form-group">
                            <label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Size</label>
                            <div class="col-sm-10">
                                <form:select class="form-control" path="size" itemLabel="name" itemValue="id" items="${sizes}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Color</label>
                            <div class="col-sm-10">
                                <form:select class="form-control" path="color" itemLabel="name" itemValue="id" items="${colors}"/>
                            </div>
                        </div>
                        <%--<c:forEach items="${nosss}" var="noss">--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label">${noss.name}</label>--%>
                                <%--<div class="col-sm-10">--%>
                                    <%--<form:select path="specificationStrings" items="${noss.specificationStrings}" itemLabel="name" itemValue="id" class="form-control" multiple="false"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
                        <%--<c:forEach items="${nosds}" var="nosd" varStatus="vs">--%>
                            <%--<form:hidden path="specificationDigitals[${vs.index}].nameOfSpecificationDigital" value="${nosd.id}"/>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="value${vs.index}" class="col-sm-offset-2 col-sm-10"><form:errors path="specificationDigitals[${vs.index}].value"/></label>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label">${nosd.name}</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<form:input id="value${vs.index}" path="specificationDigitals[${vs.index}].value" class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="specificationDigitals[${vs.index}].measuringSystems" class="form-control" multiple="false"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
                        <div class="form-group">
                            <label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Price</label>
                            <div class="col-sm-7">
                                <form:input id="price" path="price" class="form-control"/>
                            </div>
                            <%--<div class="col-sm-3">--%>
                                <%--<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="measuringSystems" class="form-control" multiple="false"/>--%>
                            <%--</div>--%>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Create</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-2 col-xs-2"><h3>Item name</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Item price</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Subcategory</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Size</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Color</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Update</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
        </div>
        <c:forEach items="${page.content}" var="item">
            <div class="row">
                <div class="col-md-2 col-xs-2">${item.name}</div>
                <div class="col-md-2 col-xs-2">${item.price}</div>
                <div class="col-md-2 col-xs-2">${item.subcategory.name}</div>
                <div class="col-md-2 col-xs-2">${item.size.name}</div>
                <div class="col-md-2 col-xs-2">${item.color.name}</div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/item/update/${item.id}">update</a></div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/item/delete/${item.id}">delete</a></div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12"></div>
</div>
<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
    </div>
</div>








<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%--<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>--%>
<%--<div class="row">--%>
    <%--<nav class="navbar navbar-default">--%>
        <%--<div class="container-fluid">--%>
            <%--<button type="button" class="navbar-toggle" data-toggle="collapse"--%>
                    <%--data-target="#myNavbar">--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
            <%--</button>--%>
            <%--<div class="collapse navbar-collapse" id="myNavbar">--%>
                <%--<ul class="nav navbar-nav">--%>
                    <%--<li><a href="/admin/basket">Basket</a></li>--%>
                    <%--<li><a href="/admin/subcategory">Subcategory</a></li>--%>
                    <%--<li><a href="/admin/color">Color</a></li>--%>
                    <%--<li><a href="/admin/size">Size</a></li>--%>
                    <%--<li><a href="/admin/category">Category</a></li>--%>
                    <%--<li><a href="/admin/users">Users</a></li>--%>
                    <%--<li class="active"><a href="/admin/item">Item</a><span--%>
                            <%--class="sr-only">(current)</span></li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</nav>--%>
<%--</div>--%>
<%--<div class="row">--%>
    <%--<div class="col-md-3 col-xs-12"></div>--%>
    <%--<div class="col-md-7 col-xs-12">--%>
        <%--<c:if test="${category ne null}">--%>
            <%--<div class="row">--%>
                <%--<div class="col-md-12 col-xs-12">--%>
                    <%--<form:form class="form-horizontal" action="/admin/item" method="POST" modelAttribute="item" >--%>
                        <%--<form:hidden value="${subcategory.id}" path="subcategory"/>--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="name" class="col-sm-2 control-label">Name</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<form:input type="text" class="form-control" path="name" id="name"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-2 control-label">Color</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<form:select class="form-control" path="color" itemLabel="name" itemValue="id" items="${colors}"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-2 control-label">Size</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<form:select class="form-control" path="size" itemLabel="name" itemValue="id" items="${sizes}"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<c:forEach items="${nosss}" var="noss">--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label">${noss.name}</label>--%>
                                <%--<div class="col-sm-10">--%>
                                    <%--<form:select path="specificationStrings" items="${noss.specificationStrings}" itemLabel="name" itemValue="id" class="form-control" multiple="false"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
                        <%--<c:forEach items="${nosds}" var="nosd" varStatus="vs">--%>
                            <%--<form:hidden path="specificationDigitals[${vs.index}].nameOfSpecificationDigital" value="${nosd.id}"/>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="value${vs.index}" class="col-sm-offset-2 col-sm-10"><form:errors path="specificationDigitals[${vs.index}].value"/></label>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label">${nosd.name}</label>--%>
                                <%--<div class="col-sm-7">--%>
                                    <%--<form:input id="value${vs.index}" path="specificationDigitals[${vs.index}].value" class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-3">--%>
                                    <%--<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="specificationDigitals[${vs.index}].measuringSystems" class="form-control" multiple="false"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-2 control-label">Price</label>--%>
                            <%--<div class="col-sm-7">--%>
                                <%--<form:input id="price" path="price" class="form-control"/>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<form:select itemLabel="name" itemValue="id" items="${measuringSystems}" path="measuringSystems" class="form-control" multiple="false"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                                <%--<button type="submit" class="btn btn-default">Create</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</form:form>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <%--<div class="row">--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Item name</h3></div>--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Item price</h3></div>--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Category</h3></div>--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Color</h3></div>--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Size</h3></div>--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Update</h3></div>--%>
            <%--<div class="col-md-2 col-xs-2"><h3>Delete</h3></div>--%>
        <%--</div>--%>
        <%--<c:forEach items="${items}" var="item">--%>
            <%--<div class="row">--%>
                <%--<div class="col-md-2 col-xs-2">${item.name}</div>--%>
                <%--<div class="col-md-2 col-xs-2">${item.price}</div>--%>
                <%--<div class="col-md-2 col-xs-2">${item.subcategory.name}</div>--%>
                <%--<div class="col-md-2 col-xs-2">${item.color.name}</div>--%>
                <%--<div class="col-md-2 col-xs-2">${item.size.name}</div>--%>
                <%--<div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/item/update/${item.id}">update</a></div>--%>
                <%--<div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/item/delete/${item.id}">delete</a></div>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>
    <%--<div class="col-md-2 col-xs-12"></div>--%>
<%--</div>--%>