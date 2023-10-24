<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 10px;
        }

        div {
            margin: 10px;
            padding: 15px;
        }

        a {
            text-decoration: none;
            color: #000000;
            margin: 5px;
        }

        button {
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 10px;
            margin: 5px;
            background-color: #c7f7da;
            color: #000000;
        }
    </style>
</head>
<body>
    <div>
        <h3>Task 1. Время суток:</h3>
        <% String pageName = "morning.jsp";
            switch (((new Date()).getHours() + 2) / 6) {
            case 0: {
                out.print("<h4>Good night</h4>");
                pageName = "night.jsp";
                break;
            }
            case 1: {
                out.print("<h4>Good morning</h4>");
                pageName = "morning.jsp";
                break;
            }
            case 2: {
                out.print("<h4>Good afternoon</h4>");
                pageName = "afternoon.jsp";
                break;
            }
            case 3: {
                out.print("<h4>Good evening</h4>");
                pageName = "evening.jsp";
                break;
            }
        }
        %>
    </div>
    <div>
        <h3>Task 2. Вывести таблицу:</h3>
        <%SimpleDateFormat yy = new SimpleDateFormat("dd.MM.yyyy"); %>
        <%SimpleDateFormat u = new SimpleDateFormat("u' ('E') '"); %>
        <table>
            <% for (int i = 0; i < 7; i++) {
                Date dateNext = new Date(new Date().getTime() + 60*60*24*1000*i);%>
            <tr>
                <td><% out.print(yy.format(dateNext)); %></td>
                <td><% out.print(u.format(dateNext)); %></td>
            </tr>
            <% } %>
        </table>
    </div>
    <div>
        <h3>Task 5. Переход на страницу в зависимости от времени суток:</h3>
        <form action="<%=pageName%>" method="get">
            <button type="submit">Press</button>
        </form>
    </div>
    <div>
        <h3>Task 6. JSP:INCLUDE</h3>
        <jsp:include page="<%=pageName%>"/>
    </div>
    <div>
        <h3>Task 7. Сервлет afternoon:</h3>
        <jsp:include page="/Afternoon"/>
    </div>
    <div>
        <h3>Task 8. Сервлет afternoon (forward):</h3>
<%--             <jsp:forward page="<%= pageName %>"/>--%>
    </div>
    <div>
        <h3>Task 11 - 13. Jjj сервлет: </h3>
        <button><a href="/lab4/Jjj?parm=forw">GET (Jjj) (forward) </a> </button> <br>
        <button><a href="/lab4/Jjj?parm=hc">GET (Jjj) (HTTPClient) </a> </button>
        <form action="/lab4/Jjj" method="post">
            <button type="submit">Post Jjj</button>
        </form>
    </div>
</body>
</html>