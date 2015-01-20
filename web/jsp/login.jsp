<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>

<html>
<head>
    <title><fmt:message key="label.title.login" bundle="${ rb }"/></title>
    <link rel="stylesheet" href="/css/style.css" media="screen" type="text/css"/>
</head>
<body>
<style>
    <%@include file='/css/default.css' %>
    <%@include file='/css/style.css' %>
</style>
<c:import url="/jsp/fragment/header.jsp"></c:import>
<div id="wrapper">
    <div id="menu" class="container">
        <ul>
            <li class="current_page_item"><a href="/home.jsp" accesskey="1" title=""><fmt:message key="home"
                                                                                                   bundle="${ rb }"/></a>
            </li>

            <c:if test="${ empty role }">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>
            </c:if>

        </ul>
    </div>
</div>


<form name="loginForm" method="POST" action="controller">
    <div class="form">
        <input type="hidden" name="command" value="login"/>
        <input type="text" class="zocial-dribbble" name="login" value=""
               placeholder="<fmt:message key="login.placeholder" bundle="${ rb }"/>"/>

        <input type="password" name="password" value=""
               placeholder="<fmt:message key="password.placeholder" bundle="${ rb }"/>"/>

        ${errorFillMessage}
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${nullPage}

        <input type="submit" value="<fmt:message key="button.enter" bundle="${ rb }"/>"/>
    </div>
</form>

<c:import url="/jsp/fragment/footer.jsp"></c:import>

</body>
</html>
