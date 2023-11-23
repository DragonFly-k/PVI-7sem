package com.example.lab10;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ccc extends HttpServlet implements Servlet 
{
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
          String Url = "jdbc:sqlserver://localhost:1433;databaseName=University;Trusted_Connection=No;user=sa;password=Vv1542139";
          try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                response.setContentType("text/html;charset=utf-8");
                System.out.println("Trying to connect");
                Connection connection = DriverManager.getConnection(Url);
                System.out.println("Connection Established Successfull");

                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Students");
                ResultSet rs = pstmt.executeQuery();

                PrintWriter printWriter = response.getWriter();
                printWriter.println("Static request: SELECT * FROM Students");
                printWriter.println("<h1>Result set:</h1><table><thead><tr><th>Id</th><th>Name</th><th>Phone</th></thead><tbody>");

                while (rs.next())
                {
                    printWriter.println(
                            "<tr><td>" +
                            rs.getString("Id") +
                            "</td><td>" +
                            rs.getString("Name") +
                            "</td><td>" +
                            rs.getString("Phone") +"</td></tr>"
                    );
                }
                printWriter.println("</tbody></table>");

            }
            catch (Exception e)
            {
                System.out.println("Unable to make connection with DB");
                e.printStackTrace();
            }



        /*
        int param1 = Integer.parseInt(request.getParameter("param1"));
        int param2 = Integer.parseInt(request.getParameter("param2"));

        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Students;user=klepaski;password=123456";

        try {
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          Connection con = DriverManager.getConnection(connectionUrl);
          PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Students where id > ? and id < ?");


          pstmt.setInt(1, param1 );
          pstmt.setInt(2,param2);
          ResultSet rs = pstmt.executeQuery();

          PrintWriter out = response.getWriter();
          while (rs.next()) {
            out.println(
              "Name:" +
                rs.getString("Name") +
                " Phone:" +
                rs.getString("Phone")
            );
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }*/
      }
}
