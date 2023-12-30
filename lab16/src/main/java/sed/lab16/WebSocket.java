package sed.lab16;

import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ServerEndpoint("/websocket")
public class WebSocket extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            while (true) {
                session.getBasicRemote().sendText(format.format(new Date()));
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}