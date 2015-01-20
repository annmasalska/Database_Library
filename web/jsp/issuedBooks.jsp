<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>

<html>
<head>
    <title><fmt:message key="issuedbooks" bundle="${ rb }"/></title>

</head>
<body>
<style>
    <%@include file='/css/default.css' %>
    <%@include file='/css/table.css' %>
</style>

<c:import url="/jsp/fragment/header.jsp"></c:import>

<div id="wrapper">
    <div id="menu" class="container">
        <ul>
            <li><a href="/home.jsp" accesskey="1" title=""><fmt:message key="home" bundle="${ rb }"/></a></li>

            <c:if test="${ role eq 'administrator'}">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>
                <li><a href="/jsp/admin/addBook.jsp" accesskey="2" title=""><fmt:message key="add.book"
                                                                                         bundle="${ rb }"/></a></li>
                <li><a href="controller?command=delete" accesskey="2" title=""><fmt:message key="delete.book"
                                                                                            bundle="${ rb }"/></a></li>
                <li><a href="controller?command=returnbook" accesskey="6" title=""><fmt:message key="returnbook"
                                                                                                bundle="${ rb }"/></a>
                </li>
            </c:if>
            <c:if test="${ not empty role }">
                <c:if test="${role ne 'administrator' }">
                    <li><a href="controller?command=print" accesskey="1" title=""><fmt:message
                            key="catalogue" bundle="${ rb }"/></a></li>
                    <li class="current_page_item"><a href="controller?command=selectissuedbooks" accesskey="2"
                                                     title=""><fmt:message key="issuedbooks" bundle="${ rb }"/></a></li>
                    <li><a href="controller?command=selectordersbyusername" accesskey="3" title=""><fmt:message
                            key="myorders" bundle="${ rb }"/></a></li>
                </c:if>
            </c:if>
            <c:if test="${ empty role }">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>
            </c:if>
        </ul>
    </div>
</div>
<c:if test="${not empty lst  }">
    <table cellspacing="0">

        <tr>
            <th><fmt:message key="borrow.info" bundle="${ rb }"/></th>
            <th><fmt:message key="book.name" bundle="${ rb }"/></th>
            <th><fmt:message key="book.author" bundle="${ rb }"/></th>
            <th><fmt:message key="book.information" bundle="${ rb }"/></th>
            <th><fmt:message key="issuedbook.date" bundle="${ rb }"/></th>
        </tr>

        <c:forEach var="elem" items="${lst}" varStatus="status">
            <tr>
                <td><c:out value="${ elem.idorder  }"/></td>
                <td><c:out value="${ elem.name }"/></td>
                <td><c:out value="${ elem.author }"/></td>
                <td><c:out value="${ elem.information }"/></td>
                <td><c:out value="${ elem.date }"/></td>
            </tr>
        </c:forEach>

    </table>
</c:if>
<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>