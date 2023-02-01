<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Movie Summary</title>
</head>
<body>
<h1>Movie Summary</h1>

<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Rated</th>
        <th>Director</th>
        <th>Awards</th>

    </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <form:form modelAttribute="movie">
                    <form:label path="title">${movie.title}</form:label><form:hidden path="title" />
                </form:form>
            </td>
            <td>
                <form:form modelAttribute="movie">
                     <form:label path="year">${movie.year}</form:label><form:hidden path="year" />
                </form:form>
            </td>
            <td>
                 <form:form modelAttribute="movie">
                      <form:label path="rated">${movie.rated}</form:label><form:hidden path="rated" />
                 </form:form>
            </td>
            <td>
                 <form:form modelAttribute="movie">
                      <form:label path="director">${movie.director}</form:label><form:hidden path="director" />
                 </form:form>
            </td>
            <td>
                 <form:form modelAttribute="movie">
                      <form:label path="awards">${movie.awards}</form:label><form:hidden path="awards" />
                 </form:form>
            </td>

        </tr>
    </tbody>
</table>
    <form:form modelAttribute="movie">
        <form:label path="plot">Plot: ${movie.plot}</form:label><form:hidden path="plot" />
    </form:form>

        <button id="return" onclick="location.href ='/movie-list'" >Return</button>

</body>
</html>