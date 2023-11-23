package sed.lab6;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Task2 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter printWriter = response.getWriter();

        String urlnParam = request.getParameter("urln");
        ServletContext sc = getServletContext();
        String uri = sc.getInitParameter("URL" + urlnParam);
        String completedUri = "http://localhost:8083/lab6/" + uri;
        if (uri != null)
        {
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest rq = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(completedUri))
                    .build();
            try {
                HttpResponse<String> rs = client.send(rq, java.net.http.HttpResponse.BodyHandlers.ofString());
                printWriter.println(rs.body());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            printWriter.println("Parameter URLn not found");
        }
    }
}
