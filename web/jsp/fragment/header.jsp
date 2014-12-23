<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>

<link href=" css/default.css" rel="stylesheet" type="text/css"/>
<div id="wrapper">
    <div id="header-wrapper">
        <div id="header" class="container">
            <div id="mainTitle"><p><fmt:message key="label.database" bundle="${ rb }"/></p></div>
            <div id="social">

                <ul class="contact">

                    <c:if test="${ empty role}">
                        <li><a href="/jsp/login.jsp">
                            <fmt:message key="label.login" bundle="${ rb }"/></a>
                        <li><a href="/jsp/registration.jsp">
                            <fmt:message key="label.registration" bundle="${ rb }"/></a></li>
                        </li>
                    </c:if>
                    <c:if test="${ not empty role}">
                        <li id="role"><fmt:message key="user" bundle="${ rb }"/> : ${role} </li>
                        <li><a href=href="controller?command=logout> <fmt:message key="label.logout"
                                                                                  bundle="${ rb }"/> </a></li>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>


</div>