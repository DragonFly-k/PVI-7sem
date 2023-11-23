package sed.lab5.Tags;

import java.io.*;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
public class Surname extends TagSupport
{
    static String in = "<label>Surname&nbsp &nbsp</label>"
                        + "<input name =\"surname\" type = \"text\" width = \"150\" "
                        + " maxlength= \"30\" ";

    public String value = "";

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public int doStartTag() throws JspException
    {
        JspWriter out = pageContext.getOut();
        try
        {
            out.print(in + (this.value.equals("") ? " " : "value =\""+this.value+"\" />" ));
        }
        catch (IOException e)
        {
            System.out.println("Surname: " + e);
        }
        return SKIP_BODY;
    }
}

