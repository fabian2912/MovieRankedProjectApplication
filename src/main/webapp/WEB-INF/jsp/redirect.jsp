<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.io.*,java.util.*"%>

<!doctype html>
<html lang="en">
<head>
    <title>Redirect</title>
</head>
<body>
    <h1>Redirecting...</h1>
    <%
        response.setIntHeader("Refresh", 1);
    %>

    <script type="text/javascript">
       function refresh() {
            window.opener.location.reload();
            window.close();
        }
    </script>
</body>
</html>