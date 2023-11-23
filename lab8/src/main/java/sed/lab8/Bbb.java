package sed.lab8;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "Bbb", value = "/Bbb")
public class Bbb extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        StringBuilder result = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        Enumeration<String> params = request.getParameterNames();

        while (headers.hasMoreElements()) 
        {
            String headerName = (String) headers.nextElement();
            if (headerName.contains("x-"))
            {
                result.append("<p>").append(headerName).append(": ").append(request.getHeader(headerName)).append("</p>");

            }
        }
        result.append("\n");

        while (params.hasMoreElements()) 
        {
            String paramName = (String) params.nextElement();
            result.append("<p>").append(paramName).append(": ").append(request.getParameter(paramName)).append("</p>");
        }

        response.addHeader("X-Bbb1", "11111");
        response.addHeader("X-Bbb2", "22222");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(result);
    }
}
