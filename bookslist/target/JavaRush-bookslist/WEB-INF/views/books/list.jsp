<%--
  Created by IntelliJ IDEA.
  User: Ganichev
  Date: 03.10.2017
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<% request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/book.css" rel="stylesheet"/>

    <title></title>
</head>
<body>
    <div class="container">
        <spring:url value="/books/add" var="addUrl" />
        <h1>Java Rush Books
            <button class="btn pull-right" onclick="location.href='${addUrl}'">Add Book</button>
        </h1>
    </div>
    <div class="container">
        <form class="form-inline" method="GET" action="/books">
            Search:
            <input  type="text" name="title" value="${titleSearch}"/>
            <button class="btn btn-default custom-button-width" type="submit">Search</button>
            <button class="btn btn-danger custom-button-width" type="submit" name="clear" value="clear">Clear</button>
        </form>
    </div>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Author</th>
                    <th>ISBN</th>
                    <th>PrintYear</th>
                    <th>readAlready</th>
                    <th>Action</th>
                </tr>
            </thead>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.description}</td>
                        <td>${book.author}</td>
                        <td>${book.isbn}</td>
                        <td>${book.printYear}</td>
                        <td>${book.readAlready}</td>
                        <td>

                            <spring:url value="/books/delete?id=${book.id}" var="deleteUrl" />
                            <spring:url value="/books/update?id=${book.id}" var="updateUrl">
                                <spring:param name="id" value="${book.id}"/>
                            </spring:url>
                            <spring:url value="/books/read?id=${book.id}" var="readUrl"/>


                            <!--button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button-->
                            <div class="form-inline">
                                <c:choose>
                                    <c:when test="${book.readAlready}">
                                        <div class="form-group">
                                            <form:form method="POST" action="${readUrl}" id="readButton">
                                                <button class="btn btn-default custom-button-width" type="submit" disabled="disabled">Read</button>
                                            </form:form>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group">
                                            <form:form method="POST" action="${readUrl}" id="readButton">
                                                <button class="btn btn-default custom-button-width" type="submit">Read</button>
                                            </form:form>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <div class="form-group">
                                    <form:form method="GET" action="/books/update" id="updateButton">
                                        <button class="btn btn-success custom-button-width" type="submit" name="id" value="${book.id}">Update</button>
                                    </form:form>
                                </div>
                                <div class="form-group">
                                    <form:form method="POST" action="${deleteUrl}" id="deleteButton">
                                        <button class="btn btn-danger  custom-button-width" type="submit">Delete</button>
                                    </form:form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
        </table>

        <c:if test="${currentPage > 1}">
            <c:set var = "prevPageNum" scope="page" value="${currentPage - 1}"/>
            <spring:url value="/books?page=${prevPageNum}${fn:length(bookFilter) > 0?'&':''}${fn:length(bookFilter) > 0?bookFilter: ''}" var="previousURL" />
            <a href="${previousURL}">Previous</a>
        </c:if>
        <c:if test="${isNextPageAvailable}">
            <c:set var = "nextPageNum" scope="page" value="${currentPage + 1}"/>
            <spring:url value="/books?page=${nextPageNum}${fn:length(bookFilter) > 0?'&':''}${fn:length(bookFilter) > 0?bookFilter: ''}" var="nextURL" />
            <a href="${nextURL}">Next</a>
        </c:if>
    </div>
</body>
</html>
