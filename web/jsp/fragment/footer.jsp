<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<div id="footer">

    <p>&copy;<fmt:message key="footer.copyright" bundle="${ rb }"/> | <a href="controller?command=locale&id=ru">RU</a> | <a href="controller?command=locale&id=en">EN</a></p>

</div>