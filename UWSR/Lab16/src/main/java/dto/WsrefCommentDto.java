package dto;

public class WsrefCommentDto {
    private String wsrefId;
    private String sessionId;
    private String comtext;

    public String getWsrefId() {
        return wsrefId;
    }

    public void setWsrefId(String wsrefId) {
        this.wsrefId = wsrefId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getComtext() {
        return comtext;
    }

    public void setComtext(String comtext) {
        this.comtext = comtext;
    }
}