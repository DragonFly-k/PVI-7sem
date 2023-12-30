package sed.lab13;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet(name = "Sss", value = "/Sss")
public class Sss extends HttpServlet
{
    BufferedWriter writer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String filename = request.getParameter("file");
        String docdir = getServletContext().getInitParameter("docs");

        if (filename == null)
        {
            Find files = new Find(docdir, "doc");
            response.setContentType("text/html;charset=utf-8");

            StringBuilder result = new StringBuilder();

            for (String file : files.list)
            {
                result.append("<br>").append("<a href='./Sss?file=").append(file).append("' >").append(file).append("</a>");
            }
            response.getWriter().println(result);
        }
        else 
        {
            System.out.println(filename);
            try 
            {
                lofFile(filename);
                File doc = new File(docdir.concat("\\").concat(filename));
                response.setContentType("application/msword");
                response.addHeader("Content-Disposition", "attachment; filename="+ doc.getName());
                response.setContentLength((int) doc.length());

                FileInputStream in = new FileInputStream(doc);
                BufferedInputStream buf = new BufferedInputStream(in);
                ServletOutputStream out = response.getOutputStream();
                int readBytes = 0;
                while ((readBytes = buf.read()) != -1)
                {
                    out.write(readBytes);
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

        }

    }

    public  void  lofFile(String fileName) throws IOException 
    {
        System.out.println("log");
        writer = new BufferedWriter(new FileWriter("..\\stdout.txt", true));
        writer.append(fileName);
        writer.append("\n");
        writer.close();
    }

}
