<%@  page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<style>
    <%@include file='/css/default.css' %>
    <%@include file='/css/style.css' %>
</style>
<html>
<head>
    <title><fmt:message key="label.error" bundle="${ rb }"/></title>
</head>
<body>
<link href=" css/default.css" rel="stylesheet" type="text/css"/>
<div id="wrapper">
    <div id="header-wrapper">
        <div id="header" class="container">
            <div id="mainTitle"><p><fmt:message key="label.error" bundle="${ rb }"/></p></div>

        </div>
    </div>


</div>
</br></br>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
<c:out value="${requestScope.errorCause}"/>
<c:forEach items="${errorCause.stackTrace}" var="element">
    <c:out value="${element}"/>
</c:forEach>
<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>



