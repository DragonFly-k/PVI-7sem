package sed.lab15;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "message", value = "/message")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            ServletContext context = request.getServletContext();
            response.getWriter().println(EmailAdmin.showMessages(context.getInitParameter("UserEmail"),
                    context.getInitParameter("UserPassword")));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        final String userEmail = context.getInitParameter("UserEmail");
        final String userPassword = context.getInitParameter("UserPassword");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, userPassword);
            }
        };
        try {
            MimeMessage msg = new MimeMessage(Session.getInstance(props, auth));

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(userEmail, "JAVA"));
            msg.setSubject(context.getInitParameter("Subject"), "UTF-8");
            msg.setText(request.getParameter("message"), "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getParameter("email"), false));
            Transport.send(msg);
            response.sendRedirect("./jsp/messageForm.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}