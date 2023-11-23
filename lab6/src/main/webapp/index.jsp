<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab 6</title>
</head>
<body>
<h3>Значение параметра URL1:</h3> <%=pageContext.getServletContext().getInitParameter("URL1")%><br/>
<h3>Значение параметра URL2:</h3><%=pageContext.getServletContext().getInitParameter("URL2")%>

<form method="post" action="./Ccc">
    <input type="text" name="value1" placeholder="value1"/><br>
    <input type="text" name="value2" placeholder="value2"/><br>
    <input type="text" name="value3" placeholder="value3"/><br>
    <input type="text" name="cbean" placeholder="cbean"/><br>
    <button>Post</button>
</form>

<form method="get" action="./Ccc">
    <button>Get</button>
</form>
</body>
</html>