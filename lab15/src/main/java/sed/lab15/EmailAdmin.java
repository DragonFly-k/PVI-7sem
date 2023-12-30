package sed.lab15;
import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.Properties;

public class EmailAdmin {
    public static String showMessages(final String userEmail, final String password) throws MessagingException, IOException
    {
        String host = "imap.gmail.com";
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.ssl.trust", "*");
        properties.put("mail.mime.charset", "utf-8");

        Store store = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(userEmail, password);
            }
        }).getStore("imaps");

        store.connect(host, userEmail, password);
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        StringBuilder result = new StringBuilder();

        result.append("<html>" +
                "<head> <meta charset=" +'"' +"utf-8" + '"' + "/>" +
                "<title>Messages</title></head><body>");
        for (Message message: folder.getMessages())
        {
            result.append("<div  style='cursor: pointer;'>");
            result.append("<p>").append(InternetAddress.toString(message.getFrom())).append("</p>");
            result.append("<p>").append(message.getSubject()).append("</p>");

            String messageContent = "";

            String contentType = message.getContentType();
            if (contentType.contains("multipart"))
            {
                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++)
                {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    messageContent = part.getContent().toString();
                }
            }
            else if (contentType.contains("text/plain") || contentType.contains("text/html"))
            {
                Object content = message.getContent();
                if (content != null)
                {
                    messageContent = content.toString();
                }
            }

            result.append("<div>").append(messageContent).append("</div>");
            result.append("<p>").append(message.getSentDate()).append("</p>");
            result.append("</div>");
            result.append("-----------------------------------------------");
            result.append("</br>");
        }
        result.append("</body></html>");

        folder.close(true);
        store.close();
        return result.toString();
    }
}