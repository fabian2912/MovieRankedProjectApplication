<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Add Movie</title>

</head>
    <body>
        <h1>We are on the poster page!</h1>

        <form:form action="/movie-list" method="post" modelAttribute="movie">
            <form:label path="Title">Title: </form:label> <form:input path="Title" type="text"/>
            <input id="submit" type="submit" value="submit">
        </form:form>

        <table>
            <thead>
            <tr>
                <th>TITLE</th>
                <th>YEAR</th>
                <th>COUNTRY</th>
                <th>SUMMARY</th>
            </tr>
            </thead>
            <tbody>
            <form action="/movie-list" method="get">
            <c:forEach items="${movieList}" var="movie">
                <tr>
                    <td>${movie.title}</td>
                    <td>${movie.year}</td>
                    <td>${movie.country}</td>
                    <td>
                        <a href = "
                                                    <c:url value = "/movie-list/summary">
                                                        <c:param name="id" value="${movie.id}" />
                                                    </c:url>
                                    ">${movie.id}</a>
                    </td>
                </tr>
            </c:forEach>
            </form>

            </tbody>
        </table>

    </body>
</html>