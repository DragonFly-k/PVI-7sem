package sed.lab11;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Random;

public class Sss_Json extends HttpServlet implements Servlet
{
      @Override
      public void init() throws ServletException
      {
            super.init();
            System.out.println("Sss_Json.Init");
      }

      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
            System.out.println("Sss_Json. Get");
      }

      @Override
      protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
            try
            {
                Random random = new Random();
                int n = Integer.parseInt(request.getHeader("XRand-N"));
                StringBuilder textResult = new StringBuilder();
                int count = random.nextInt(6) + 5;

                textResult.append("[");

                for (int i = 0; i < count; i++)
                {
                    int number = random.nextInt(n*2) - n;
                    textResult.append(number).append(i < count -1 ? "," : "");
                }
                textResult.append("]");

                Thread.sleep(1000);

                response.setContentType("application/json");
                response.getWriter().println(textResult);

            }
            catch (Exception e)
            {
                response.getWriter().println(e.getMessage());
            }
        }
}
