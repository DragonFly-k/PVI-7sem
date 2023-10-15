package com.example.lab2;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Sss extends HttpServlet {
    public void init(ServletConfig sc) throws ServletException {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }

    protected void service(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        System.out.println("service " + rq.getMethod());
        PrintWriter pw = rs.getWriter();
        pw.println("Service " + rq.getMethod());
        pw.println("ServerName: " + rq.getServerName());
        pw.println("Local Address: " + rq.getLocalAddr());
        pw.println("FirstName: " + rq.getParameter("firstname"));
        pw.println("LastName: " + rq.getParameter("lastname"));
        pw.close();
    }

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        System.out.println("SSS: get");

        PrintWriter pw = rs.getWriter();
        pw.println("Sss: " + rq.getMethod());
        pw.println("FirstName: " + rq.getParameter("firstname"));
        pw.println("LastName: " + rq.getParameter("lastname"));
        pw.close();
    }
}