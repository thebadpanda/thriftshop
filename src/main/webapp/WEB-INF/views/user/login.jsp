<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

    <div class="row">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/">Main</a> </li>
                <li><a href="/">До списку товарів</a></li>
                <li><a href="/login"> Login</a> </li>
            </ul>
        </div>
    </div>
    <div class="row">
        <c:if test="${param.fail}">
            <div class="col-sm-12 col-xs-12 text-center">
                Fail to authorize!!!
            </div>
        </c:if>
        <div class="col-md-12"><br></div>
        <div class="col-sm-12 col-xs-12">
            <form:form class="form-horizontal" action="/login" method="POST">
                <div class="form-group">
                    <label for="login" class="col-sm-2 control-label">Логін</label>
                    <div class="col-sm-10">
                        <input class="form-control" name="login" id="login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Пароль</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password" id="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input name="remember-me" type="checkbox"> Запамятати
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Увійти</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>