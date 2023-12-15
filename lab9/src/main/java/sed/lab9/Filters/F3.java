package sed.lab9.Filters;

import jakarta.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class F3 implements Filter
{
      @Override
      public void init(FilterConfig filterConfig) throws ServletException
      {
        System.out.println("F3. Init");
      }

      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
      {
        if (servletRequest.getParameter("value3").equals("3"))
        {
          System.out.println("F3. DoFilter");
          filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
          PrintWriter out = servletResponse.getWriter();
          out.println("F3, value3!=3");
        }
      }

      @Override
      public void destroy()
      {
        System.out.println("F3. Destroy");
      }
}
