package sed.lab3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
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
import java.time.Duration;

public class Sss extends HttpServlet {
    public Sss()
    {
        super();
        System.out.println("Sss:constructor");
    }

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        System.out.println("Sss:init");
    }

    @Override
    public void destroy()
    {
        super.destroy();
        System.out.println("Sss:destroy");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.service(req, resp);
        System.out.println("Sss:service");

        RequestDispatcher requestDispatcher = null;
        String parm = req.getParameter("parm");
        PrintWriter printWriter = resp.getWriter();

        switch (parm) {
            case ("MyParameter"):
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
                break;
            case ("GetGggRedirect"):
                resp.sendRedirect("Ggg?parm=GetGggRedirect");
                break;
            case ("PostGggForward"):
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
                break;
            case ("PostGggRedirect"):
                resp.setStatus(307);
                resp.setHeader("Location", "http://localhost:8082/lab3/Ggg?parm=PostGggRedirect");
                break;
            case ("GetHtmlForward"):
                requestDispatcher = req.getRequestDispatcher("page.html");
                requestDispatcher.forward(req, resp);
                break;
            case ("GetHtmlRedirect"):
                resp.sendRedirect("page.html");
                break;
            case ("GetTwoHtmlForward"):
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
                break;
            case ("GetTwoHtmlRedirect"):
                resp.sendRedirect("Ggg?parm=GetTwoHtmlRedirect");
                break;
            case ("GetTwoInfoForward"):
                printWriter.write("Info from Sss");
                requestDispatcher = req.getRequestDispatcher("Ggg");
                requestDispatcher.forward(req, resp);
                break;
            case ("GetTwoInfoRedirect"):
                printWriter.write("Info from Sss");
                resp.sendRedirect("Ggg?parm=");
                break;
            case ("HttpClientGet"):
                HttpClient client = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_1_1)
                        .connectTimeout(Duration.ofSeconds(10))
                        .build();

                HttpRequest rq = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:8082/lab3/Ggg?parm=HttpClient&name=Kate"))
                        .build();

                try {
                    HttpResponse<String> rs = client.send(rq, java.net.http.HttpResponse.BodyHandlers.ofString());

                    resp.getWriter().write(rs.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case ("HttpClientGetRemote"):
                HttpClient remoteClient = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_1_1)
                        .connectTimeout(Duration.ofSeconds(10))
                        .build();

                HttpRequest remoteRq = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("http://localhost:8083/lab3/Ggg?parm=HttpClient&name=Kate"))
                        .build();

                try {
                    HttpResponse<String> remoteRs = remoteClient.send(remoteRq, java.net.http.HttpResponse.BodyHandlers.ofString());

                    resp.getWriter().write(remoteRs.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case ("HttpClientPost"):
                HttpClient client2 = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_1_1)
                        .connectTimeout(Duration.ofSeconds(10))
                        .build();

                HttpRequest rq2 = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofInputStream(() -> {
                            try {
                                return req.getInputStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }))
                        .uri(URI.create("http://localhost:8082/lab3/Ggg?parm=Kate"))
                        .build();

                try {
                    HttpResponse<String> rs = client2.send(rq2, java.net.http.HttpResponse.BodyHandlers.ofString());
                    resp.getWriter().write(rs.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>Sss: doGet</h1>");
        System.out.println("Sss:doGet");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>Sss: doPost</h1>");
        System.out.println("Sss:doPost");
    }
}
