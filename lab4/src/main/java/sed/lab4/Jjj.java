package sed.lab4;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Date;

public class Jjj extends HttpServlet {

    @Override
    public void init() throws ServletException
    {
        super.init();
        System.out.println("Jjj:init");
    }

    @Override
    public void destroy()
    {
        super.destroy();
        System.out.println("Jjj:destroy");
    }

    // 12
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj:doGet");
        forward(req, resp);
    }

    // 13
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj:doPost");
        forward(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if ("forw".equals(req.getParameter("param"))) {
            String pageName = "night.jsp";
            int hour = ((new Date()).getHours() + 2) / 6;
            switch (hour) {
                case 0: {
                    pageName = "night.jsp";
                    break;
                }
                case 1: {
                    pageName = "morning.jsp";
                    break;
                }
                case 2: {
                    pageName = "afternoon.jsp";
                    break;
                }
                case 3: {
                    pageName = "evening.jsp";
                    break;
                }
            }
            req.getRequestDispatcher(pageName).forward(req, resp);
        } else {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("http://localhost:8082/lab4/Jjj?param=forw")))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.println(response.body());
                pw.flush();
            } catch (InterruptedException | IOException e) {

            }
        }
    }
}