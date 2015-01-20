<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>

<html>
<head>
    <title><fmt:message key="label.title.registration" bundle="${ rb }"/></title>
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
<form name="registrationForm" method="POST" action="controller">
    <div class="form">
        <input type="hidden" name="command" value="registration"/>
        <input type="text" class="zocial-dribbble" name="username" value=""
               placeholder="<fmt:message key="registration.login.placeholder" bundle="${ rb }"/>"/>
        <input type="text" name="mail" value=""
               placeholder="<fmt:message key="mail.placeholder" bundle="${ rb }"/>"/>
        <input type="text" name="phone" value=""
               placeholder="<fmt:message key="phone.placeholder" bundle="${ rb }"/>"/>
        <input type="password" name="pass" value=""
               placeholder="<fmt:message key="password.placeholder" bundle="${ rb }"/>"/>
        <input type="password" name="pass_repeat" value=""
               placeholder="<fmt:message key="password.placeholder.repeat" bundle="${ rb }"/>"/>

        <div class="message">
            <fmt:message key="registration.message" bundle="${ rb }"/></div>

        ${errorFillMessage}
        ${repeatPasswordError}
        ${errorLoginMessage}
        ${passwordError}
        <input type="submit" value="<fmt:message key="button.newuser" bundle="${ rb }"/>"/>
    </div>
</form>
<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>
