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
            <li><a href="/index.jsp" accesskey="1" title=""><fmt:message key="home" bundle="${ rb }"/></a></li>

            <c:if test="${ role eq 'administrator'}">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>
                <li class="current_page_item"><a href="/jsp/addBook.jsp" accesskey="2" title=""><fmt:message
                        key="add.book" bundle="${ rb }"/></a></li>
                <li><a href="controller?command=delete" accesskey="2" title=""><fmt:message key="delete.book"
                                                                                            bundle="${ rb }"/></a></li>
                <li><a href="/jsp/addReader.jsp" accesskey="4" title=""><fmt:message key="add.reader"
                                                                                     bundle="${ rb }"/></a></li>
                <li><a href="/jsp/addBorrowInfo.jsp" accesskey="5" title=""><fmt:message key="addborrow"
                                                                                         bundle="${ rb }"/></a></li>
            </c:if>
            <c:if test="${ not empty role }">
                <c:if test="${role ne 'administrator' }">
                    <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                               bundle="${ rb }"/></a>
                    </li>
                    <li><a href="controller?command=selectborrowinfobyusername" accesskey="2" title=""><fmt:message
                            key="issue.books" bundle="${ rb }"/></a></li>
                </c:if>
            </c:if>
            <c:if test="${ empty role }">
                <li><a href="controller?command=print" accesskey="1" title=""><fmt:message key="catalogue"
                                                                                           bundle="${ rb }"/></a></li>


            </c:if>

        </ul>
    </div>
</div>

<form name="addBookForm" method="POST" action="controller">
    <div class="form">
        <input type="hidden" name="command" value="addBook"/>

        <input type="text" class="zocial-dribbble" name="id" value="" placeholder="Id"/>

        <input type="text" name="name" value="" placeholder="<fmt:message key="book.name" bundle="${ rb }"/> "/>
        <input type="text" name="author" value="" placeholder="<fmt:message key="book.author" bundle="${ rb }"/>"/>

        <input type="text" name="amount" value="" placeholder="<fmt:message key="book.amount" bundle="${ rb }"/>"/>
        <input type="text" name="information" value=""
               placeholder="<fmt:message key="book.information" bundle="${ rb }"/>"/>
        <select id="mymenu" name="genreID">
            <option value="1"><fmt:message key="genre.fantastic" bundle="${ rb }"/></option>
            <option value="2"><fmt:message key="genre.adventure" bundle="${ rb }"/></option>
            <option value="3"><fmt:message key="genre.detective" bundle="${ rb }"/></option>
            <option value="4"><fmt:message key="genre.novel" bundle="${ rb }"/></option>
        </select>
        ${errorFillMessage}
        ${successMessage}
        ${nullPage}

        <input type="submit" value="<fmt:message key="button.addbook" bundle="${ rb }"/>"/>
    </div>
</form>
${successMessage}
<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>