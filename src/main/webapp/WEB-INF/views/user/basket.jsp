<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

<div class="container">
    <div class="row">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/">До списку товарів</a></li>
                <li><a href="/login"> Login</a> </li>
            </ul>
        </div>
    </div>
    <div class="row">
        <c:if test="${param.fail}">
            <div class="col-sm-12 col-xs-12 text-center">
                Failure in basket
            </div>
        </c:if>
        <div class="col-md-12 col-xs-12"><br></div>
                <div class="row">
                    <div class="col-md-12 col-xs-12">
                        <form:form class="form-horizontal" action="/users/basket" method="POST" modelAttribute="basket">
                            <custom:hiddenInputs excludeParams="name"/>
                            <div class="form-group">
                                <label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">Name</label>
                                <div class="col-sm-10">
                                    <form:input class="form-control" path="name" id="name"/>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
        </div>
    </div>
</div>
