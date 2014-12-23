<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<html>
<head>
    <title><fmt:message key="label.title.catalogue" bundle="${ rb }"/></title>

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
            <li><a href="/index.jsp" accesskey="1" title=""><fmt:message key="home" bundle="${ rb }"/></a></li>

            <c:if test="${ role eq 'administrator'}">
                <li class="current_page_item"><a href="controller?command=print" accesskey="1" title=""><fmt:message
                        key="catalogue" bundle="${ rb }"/></a></li>
                <li><a href="/jsp/addBook.jsp" accesskey="2" title=""><fmt:message key="add.book" bundle="${ rb }"/></a>
                </li>
                <li><a href="controller?command=delete" accesskey="2" title=""><fmt:message key="delete.book"
                                                                                            bundle="${ rb }"/></a></li>
                <li><a href="/jsp/addReader.jsp" accesskey="4" title=""><fmt:message key="add.reader"
                                                                                     bundle="${ rb }"/></a></li>
            </c:if>
            <c:if test="${ not empty role }">
                <c:if test="${role ne 'administrator' }">
                    <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                               bundle="${ rb }"/></a>
                    </li>
                    <li class="current_page_item"><a href="controller?command=selectborrowinfobyusername" accesskey="2"
                                                     title=""><fmt:message key="issue.books" bundle="${ rb }"/> </a>
                    </li>
                    <li ><a href="controller?command=delete" accesskey="3" title=""><fmt:message
                            key="order" bundle="${ rb }"/></a></li>
                </c:if>
            </c:if>
            <c:if test="${ empty role }">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>


            </c:if>
        </ul>
    </div>
</div>
<div id="buttons_catalog">
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="selectbygenre"/>
        <select id="mymenu" name="genreID">

            <option value="" disabled selected><fmt:message key="genre.choose" bundle="${ rb }"/></option>
            <option value="1"><fmt:message key="genre.fantastic" bundle="${ rb }"/></option>
            <option value="2"><fmt:message key="genre.adventure" bundle="${ rb }"/></option>
            <option value="3"><fmt:message key="genre.detective" bundle="${ rb }"/></option>
            <option value="4"><fmt:message key="genre.novel" bundle="${ rb }"/></option>
            <option value="5"><fmt:message key="genre.philosophy" bundle="${ rb }"/></option>
            <option value="6"><fmt:message key="genre.antique" bundle="${ rb }"/></option>
            <option value="7"><fmt:message key="genre.drama" bundle="${ rb }"/></option>

        </select>
        <input type="submit" value="<fmt:message key="button.printbooksbygenre" bundle="${ rb }"/>"/>

    </form>
</div>


<table cellspacing="0">

    <tr>

        <th><fmt:message key="borrow.info" bundle="${ rb }"/></th>
        <th><fmt:message key="book.name" bundle="${ rb }"/></th>
        <th><fmt:message key="book.author" bundle="${ rb }"/></th>
        <th><fmt:message key="book.information" bundle="${ rb }"/></th>
        <th><fmt:message key="borrow.date" bundle="${ rb }"/></th>
        <th><fmt:message key="borrow.status" bundle="${ rb }"/></th>

    </tr>

    <c:forEach var="elem" items="${lst}" varStatus="status">
        <tr>

            <td><c:out value="${ elem.idissue  }"/></td>
            <td><c:out value="${ elem.name }"/></td>
            <td><c:out value="${ elem.author }"/></td>
            <td><c:out value="${ elem.information }"/></td>
            <td><c:out value="${ elem.date }"/></td>
            <c:if test="${ elem.status eq '1' }">
                <td><fmt:message key="borrow.status_1" bundle="${ rb }"/></td>
            </c:if>
            <c:if test="${ elem.status eq '2' }">
                <td><fmt:message key="borrow.status_2" bundle="${ rb }"/></td>
            </c:if>
            <c:if test="${ elem.status eq '3' }">
                <td><fmt:message key="borrow.status_3" bundle="${ rb }"/></td>
            </c:if>
        </tr>

    </c:forEach>

</table>


<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>
