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
            <li><a href="/home.jsp" accesskey="1" title=""><fmt:message key="home" bundle="${ rb }"/></a></li>

            <c:if test="${ role eq 'administrator'}">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>
                <li class="current_page_item"><a href="/jsp/admin/addBook.jsp" accesskey="2" title=""><fmt:message
                        key="add.book" bundle="${ rb }"/></a></li>
                <li><a href="controller?command=delete" accesskey="2" title=""><fmt:message key="delete.book"
                                                                                            bundle="${ rb }"/></a></li>

                <li><a href="controller?command=confirmorder" accesskey="5" title=""><fmt:message key="addborrow"
                                                                                                  bundle="${ rb }"/></a>
                </li>
                <li><a href="controller?command=returnbook" accesskey="6" title=""><fmt:message key="returnbook"
                                                                                                bundle="${ rb }"/></a>
                </li>
            </c:if>

        </ul>
    </div>
</div>

<form name="addBookForm" method="POST" action="controller">
    <div class="form">
        <input type="hidden" name="command" value="addBook"/>


        <input type="text" name="name" value="" placeholder="<fmt:message key="book.name" bundle="${ rb }"/> "/>
        <input type="text" name="author" value="" placeholder="<fmt:message key="book.author" bundle="${ rb }"/>"/>

        <input type="text" name="amount" value="" placeholder="<fmt:message key="book.amount" bundle="${ rb }"/>"/>
        <input type="text" name="information" value=""
               placeholder="<fmt:message key="book.information" bundle="${ rb }"/>"/>
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

        <div class="message">
            <fmt:message key="registration.message" bundle="${ rb }"/></div>
        ${errorFillMessage}
        ${successMessage}
        ${nullPage}

        <input type="submit" value="<fmt:message key="button.addbook" bundle="${ rb }"/>"/>
    </div>
</form>

<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>