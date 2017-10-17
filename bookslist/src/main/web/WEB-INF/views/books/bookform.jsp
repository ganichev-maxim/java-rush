<%--
  Created by IntelliJ IDEA.
  User: Ganichev
  Date: 05.10.2017
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/book.css" rel="stylesheet"/>
    <title></title>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${bookForm['new']}">
                <h1>Add Book</h1>
            </c:when>
            <c:otherwise>
                <h1>Update Book</h1>
            </c:otherwise>
        </c:choose>

        <spring:url value="/books" var="booksUrl" />

        <form:form class="form-horizontal" method="post" modelAttribute="bookForm" action="${booksUrl}" acceptCharset="UTF-8">

            <form:hidden path="id" />

            <spring:bind path="title">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Title</label>
                    <div class="col-sm-10">
                        <form:input path="title" type="text" class="form-control" id="title" placeholder="Book title" maxlength="100"/>
                        <form:errors path="title" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="description">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Description</label>
                    <div class="col-sm-10">
                        <form:input path="description" type="text" class="form-control" id="description" placeholder="Bookdescription" maxlength="255"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="author">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Author</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${bookForm['new']}">
                                <form:input path="author" type="text"  class="form-control" id="author" placeholder="Book author" maxlength="100"/>
                            </c:when>
                            <c:otherwise>
                                <form:input path="author" type="text" readonly="true" class="form-control" id="author" placeholder="Book author" maxlength="100"/>
                            </c:otherwise>
                        </c:choose>
                        <form:errors path="author" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="isbn">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">ISBN</label>
                    <div class="col-sm-10">
                        <form:input path="isbn" type="text" class="form-control" id="isbn" placeholder="Book ISBN" maxlength="20"/>
                        <form:errors path="isbn" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="printYear">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Year</label>
                    <div class="col-sm-10">
                        <form:input path="printYear" type="number" class="form-control" id="printYear" placeholder="1999" maxlength="4" />
                        <form:errors path="printYear" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <form:hidden path="readAlready" />

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">


                    <c:choose>
                        <c:when test="${bookForm['new']}">
                            <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                        </c:otherwise>
                    </c:choose>

                    <button class="btn-danger pull-right btn-lg pull-right" name="back" value="back">Back</button>

                </div>
            </div>

        </form:form>
    </div>
</body>
</html>
