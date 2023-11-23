package sed.lab6;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "Ccc", value = "/Ccc")
public class Ccc extends HttpServlet
{
    @Override
    public void init() throws ServletException
    {
        super.init();
        getServletContext().setAttribute("attrCBean", new CBean());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/ccc.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        getResult(request, response);
    }

    private void getResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String value1 = request.getParameter("value1");
        String value2 = request.getParameter("value2");
        String value3 = request.getParameter("value3");
        String cbean = request.getParameter("cbean");

        if (cbean != null && cbean.equals("new"))
        {
            System.out.println("New CBean");
            getServletContext().setAttribute("attrCBean", new CBean());
            setValues(value1, value2, value3);
        }
        else System.out.println("Old CBean");

        getServletContext().getAttribute("attrCBean");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/ccc.jsp");
        requestDispatcher.forward(request, response);
    }

    private void setValues(String value1, String value2, String value3)
    {
        CBean ob = (CBean) getServletContext().getAttribute("attrCBean");

        if (!Objects.equals(value1, ""))
        {
            ob.setValue1(value1);
        }

        if (!Objects.equals(value2, ""))
        {
            ob.setValue2(value2);
        }

        if (!Objects.equals(value3, ""))
        {
            ob.setValue3(value3);
        }
    }
}
