<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Movie Ranker</title>

</head>
    <body>
        <h1>Movie Ranker</h1>

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
                <th>VOTES</th>
            </tr>
            </thead>
            <tbody>
            <form action="/movie-list/vote" method="post" modelAttribute="movie">
            <c:forEach items="${movieList}" var="movie">
            <tr id = ${movie.id}>
                    <td>${movie.title}</td>
                    <td>${movie.year}</td>
                    <td>${movie.country}</td>
                    <td>
                        <a id="summaryPageLink" href = "
                                                    <c:url value = "/movie-list/summary">
                                                        <c:param name="id" value="${movie.id}" />
                                                    </c:url>
                                    ">${movie.id}</a>
                    </td>
                    <td>${movie.votes}</td>


                </tr>
            </c:forEach>

            </form>


        </tbody>
        </table>
    </body>
</html>