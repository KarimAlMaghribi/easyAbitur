package de.l.stadtwerke.loga3jobofferservice.dto.response;

public class ResponseFileMessage {
    private String message;

    public ResponseFileMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
