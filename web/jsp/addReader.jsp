<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>

<html>
<head>
    <title><fmt:message key="label.title.addbook" bundle="${ rb }"/></title>
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
            <li ><a href="/index.jsp" accesskey="1" title=""><fmt:message key="home" bundle="${ rb }"/></a></li>

            <c:if test="${ role eq 'administrator'}">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue" bundle="${ rb }"/></a></li>
                <li ><a href="/jsp/addBook.jsp" accesskey="2" title=""><fmt:message key="add.book" bundle="${ rb }"/></a></li>
                <li><a href="controller?command=delete" accesskey="2" title=""><fmt:message key="delete.book" bundle="${ rb }"/></a></li>
                <li class="current_page_item"><a href="/jsp/addReader.jsp" accesskey="4" title=""><fmt:message key="add.reader" bundle="${ rb }"/></a></li>
            </c:if>
            <c:if test="${ not empty role }">
                <c:if test="${role ne 'administrator' }">
                    <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue" bundle="${ rb }"/></a></li>
                    <li><a href="#" accesskey="2" title=""><fmt:message key="issue.books" bundle="${ rb }"/></a></li>
                </c:if>
            </c:if>
            <c:if test="${ empty role }">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue" bundle="${ rb }"/></a></li>


            </c:if>

        </ul>
    </div></div>

<form name="addReaderForm" method="POST" action="controller">
    <div class="form">
        <input type="hidden" name="command" value="addReader"/>
        <input type="text" class="zocial-dribbble" name="idcard" value="" placeholder="<fmt:message key="idcard.reader" bundle="${ rb }"/>"/>
        <input type="text" name="lastname" value="" placeholder="<fmt:message key="lastname.reader" bundle="${ rb }"/> "/>
        <input type="text" name="name" value="" placeholder="<fmt:message key="name.reader" bundle="${ rb }"/> "/>
        <input type="text" name="secondname" value="" placeholder="<fmt:message key="secondname.reader" bundle="${ rb }"/>"/>
        <input type="text" name="address" value="" placeholder="<fmt:message key="address.reader" bundle="${ rb }"/>"/>
        <input type="text" name="phone" value="" placeholder="<fmt:message key="phone.reader" bundle="${ rb }"/>"/>

        ${errorMessage}
        ${wrongAction}
        ${nullPage}

        <input type="submit" value="<fmt:message key="button.addreader" bundle="${ rb }"/>"/>
    </div>
</form>
<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>