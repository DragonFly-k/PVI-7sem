package sed.lab9.Filters;

import jakarta.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class F2 implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
      System.out.println("F2. Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      if (servletRequest.getParameter("value2").equals("2"))
      {
        System.out.println("F2. DoFilter");
        filterChain.doFilter(servletRequest, servletResponse);
      }
      else {
        PrintWriter out = servletResponse.getWriter();
        out.println("F2, value2!=2");
      }
    }

    @Override
    public void destroy()
    {
      System.out.println("F2. Destroy");
    }
}
