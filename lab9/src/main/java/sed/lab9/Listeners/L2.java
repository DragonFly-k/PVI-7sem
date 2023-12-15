package sed.lab9.Listeners;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

public class L2 implements HttpSessionAttributeListener
{
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent)
    {
      System.out.println("Attribute added");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent)
    {
      System.out.println("Attribute removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent)
    {
      System.out.println("Attribute replaced");
    }
}
