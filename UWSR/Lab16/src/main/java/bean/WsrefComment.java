package bean;

import java.sql.Timestamp;

public class WsrefComment {
    private int id;
    private int wsrefId;
    private String sessionId;
    private Timestamp stamp;
    private String comtext;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWsrefId() {
        return wsrefId;
    }

    public void setWsrefId(int wsrefId) {
        this.wsrefId = wsrefId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getStamp() {
        return stamp;
    }

    public void setStamp(Timestamp stamp) {
        this.stamp = stamp;
    }

    public String getComtext() {
        return comtext;
    }

    public void setComtext(String comtext) {
        this.comtext = comtext;
    }
}