package sed.lab10;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Bbb extends HttpServlet implements Servlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param1 = request.getParameter("param1");

        String Url = "jdbc:sqlserver://localhost:1433;databaseName=SED;Trusted_Connection=No;user=sa;password=Ks7631738";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            response.setContentType("text/html;charset=utf-8");
            System.out.println("Trying to connect");
            Connection connection = DriverManager.getConnection(Url);
            System.out.println("Connection Established Successfully");

            try (CallableStatement cstmt = connection.prepareCall("{call GetStudentNotes(?)}")) {
                cstmt.setString(1, param1);
                PrintWriter printWriter = response.getWriter();

                printWriter.println("<html><body><table><tr><th>Student Name</th><th>Subject</th><th>Note</th></tr>");

                try (ResultSet rs = cstmt.executeQuery()) {
                    while (rs.next()) {
                        String studentName = rs.getString("StudentName");
                        String subject = rs.getString("subject");
                        String note = rs.getString("note");

                        printWriter.println("<tr><td>" + studentName
                                + "</td><td>" + subject
                                + "</td><td>" + note + "</td></tr>");
                    }
                }
                printWriter.println("</table></body></html>");
            }

        } catch (Exception e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
    }
}
