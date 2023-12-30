<%@ page import="sed.lab15.EmailAdmin" %>
<%@ page import="javax.mail.MessagingException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab 15</title>
</head>
<body>
<%
    try
    {
        out.println
                (
                        EmailAdmin.showMessages(application.getInitParameter("UserEmail"),
                                application.getInitParameter("UserPassword"))
                );
    }
    catch (MessagingException e)
    {
        e.printStackTrace();
    }
%>
<a href="./jsp/messageForm.jsp">Message</a></body>
</html>
