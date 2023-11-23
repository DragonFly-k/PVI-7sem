<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page import="sed.lab6.CBean" %>
<html>
<head>
    <title>
        Cbean page
    </title>
</head>
<body>
<h3>CBean: </h3>
<%
    CBean ob = (CBean) pageContext.getServletContext().getAttribute("attrCBean");
%>
<h3>Value1: </h3><%=ob.getValue1()%>
<h3>Value2: </h3><%=ob.getValue2()%>
<h3>Value3: </h3><%=ob.getValue3()%>
</body>
</html>