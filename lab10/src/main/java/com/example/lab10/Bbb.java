package com.example.lab10;

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
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {

            String param1 = request.getParameter("param1");

          String Url = "jdbc:sqlserver://localhost:1433;databaseName=University;Trusted_Connection=No;user=sa;password=Vv1542139";
          try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                response.setContentType("text/html;charset=utf-8");
                System.out.println("Trying to connect");
                Connection connection = DriverManager.getConnection(Url);
                System.out.println("Connection Established Successfull");


                CallableStatement cstmt = connection.prepareCall("{call GetStudentByName(?)}");
                cstmt.setString(1, param1);
                PrintWriter printWriter = response.getWriter();
                try (ResultSet rs = cstmt.executeQuery()) {
                    while (rs.next()) {
                        String phone = rs.getString("phone");
                        printWriter.println("Phone Number for " + param1 + ": " + phone);
                    }
                }

            }
            catch (Exception e)
            {
                System.out.println("Unable to make connection with DB");
                e.printStackTrace();
            }
      }
}
