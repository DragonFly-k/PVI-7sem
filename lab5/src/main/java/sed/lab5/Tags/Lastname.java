package sed.lab5.Tags;

import java.io.*;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class Lastname extends TagSupport
{
    static String in = "<label>Lastname&nbsp &nbsp</label>"
            + "<input name =\"lastname\" type = \"text\" width = \"150\" "
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
            System.out.println("Lastname: " + e);
        }
        return SKIP_BODY;
    }
}

