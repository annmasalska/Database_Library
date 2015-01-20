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
            <li><a href="/home.jsp" accesskey="1" title=""><fmt:message key="home" bundle="${ rb }"/></a></li>

            <c:if test="${ role eq 'administrator'}">
                <li class="current_page_item"><a href="controller?command=print" accesskey="1" title=""><fmt:message
                        key="catalogue" bundle="${ rb }"/></a></li>
                <li><a href="/jsp/admin/addBook.jsp" accesskey="2" title=""><fmt:message key="add.book" bundle="${ rb }"/></a>
                </li>
                <li><a href="controller?command=delete" accesskey="2" title=""><fmt:message key="delete.book"
                                                                                            bundle="${ rb }"/></a></li>

                <li><a href="controller?command=confirmorder" accesskey="5" title=""><fmt:message key="addborrow"
                                                                                                  bundle="${ rb }"/></a>
                </li>
                <li><a href="controller?command=returnbook" accesskey="6" title="">Вернуть книгу</a></li>
            </c:if>
            <c:if test="${ not empty role }">
                <c:if test="${role ne 'administrator' }">
                    <li class="current_page_item"><a href="controller?command=print" accesskey="1" title=""><fmt:message
                            key="catalogue" bundle="${ rb }"/></a></li>
                    <li><a href="controller?command=selectissuedbooks" accesskey="2" title=""><fmt:message
                            key="issuedbooks" bundle="${ rb }"/> </a></li>
                    <li><a href="controller?command=selectordersbyusername" accesskey="3" title=""><fmt:message
                            key="myorders" bundle="${ rb }"/></a></li>
                </c:if>
            </c:if>
            <c:if test="${ empty role }">
                <li class="current_page_item"><a href="controller?command=print" accesskey="1" title=""><fmt:message
                        key="catalogue"
                        bundle="${ rb }"/></a></li>


            </c:if>
        </ul>
    </div>
</div>
<style>
    <%@include file='/css/search.css' %>


</style>
<form name="searchForm" method="POST" action="controller" class="search">

    <input type="hidden" name="command" value="searchByAuthor"/>
    <input type="search" name="author" placeholder="<fmt:message key="search.author.placeholder" bundle="${ rb }"/>"
           class="input"/>


    <input type="submit" name="" value="<fmt:message key="search.button" bundle="${ rb }"/>" class="submit"/>

</form>
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
${errorFillMessage}
${ errorSearchMessage}
${successMessage}
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="order"/>
    <c:if test="${not empty lst  }">
    <table cellspacing="0">

        <tr>
            <c:if test="${ not empty role &&  role ne 'administrator' }">
                <th></th>
            </c:if>
            <th><fmt:message key="book.idnumber" bundle="${ rb }"/></th>
            <th><fmt:message key="book.name" bundle="${ rb }"/></th>
            <th><fmt:message key="book.author" bundle="${ rb }"/></th>
            <th><fmt:message key="book.genre" bundle="${ rb }"/></th>
            <th><fmt:message key="book.amount" bundle="${ rb }"/></th>
            <th><fmt:message key="book.information" bundle="${ rb }"/></th>

        </tr>
        <tr>
        </tr>
        </c:if>
        <c:forEach var="elem" items="${lst}" varStatus="status">
            <tr>
                <c:if test="${ not empty role &&  role ne 'administrator' }">
                    <td><input type="checkbox" name="id" value="${ elem.id  }"><span></span></td>
                </c:if>
                <td><c:out value="${ elem.id  }"/></td>
                <td><c:out value="${ elem.name }"/></td>
                <td><c:out value="${ elem.author }"/></td>
                <td><c:out value="${ elem.genreID }"/></td>
                <td><c:out value="${ elem.amount }"/></td>
                <td><c:out value="${ elem.information }"/></td>

            </tr>
        </c:forEach>
    </table>
    </br></br>
    <c:if test="${ not empty role &&  role ne 'administrator' }">
        <input type="submit" value="<fmt:message key="button.order" bundle="${ rb }"/>"/>
    </c:if>
</form>

<c:import url="/jsp/fragment/footer.jsp"></c:import>
</body>
</html>
