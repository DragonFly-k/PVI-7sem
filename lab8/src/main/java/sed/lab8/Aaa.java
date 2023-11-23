package sed.lab8;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Aaa", value = "/Aaa")
public class Aaa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpClient client = HttpClient.newHttpClient();
        String param1 = "1";
        String param2 = "2";
        String param3 = "3";

        String requestBody = "x-Aaa1=" + param1 + "&x-Aaa2=" + param2 + "&x-Aaa3=" + param3;
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:8080/lab8/Bbb")))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("x-Aaa1", "1")
                .header("x-Aaa2", "2")
                .header("x-Aaa3", "3")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            response.setContentType("text/html");
            HttpHeaders headers = resp.headers();
            PrintWriter pw = response.getWriter();
            pw.println(resp.body());
            pw.println("<table border=\"1\">");
            pw.println("<tr><th>Header Name</th><th>Header Value</th></tr>");

            for (Map.Entry<String, List<String>> entry : headers.map().entrySet()) {
                String headerName = entry.getKey();
                List<String> headerValues = entry.getValue();
                for (String headerValue : headerValues) {
                    pw.println("<tr><td>" + headerName + "</td><td>" + headerValue + "</td></tr>");
                }
            }

            pw.println("</table>");
            pw.flush();
        } catch (InterruptedException | IOException e) {

        }
    }
}
