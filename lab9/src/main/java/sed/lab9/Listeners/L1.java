package sed.lab9.Listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class L1 implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
      System.out.println("Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
      System.out.println("Context destroyed");
    }
}
