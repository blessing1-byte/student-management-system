package org.example.registration.util;

public class Response {
    private final Boolean isSuccessful;
private final String message;
private final Object data;

    public Response(Boolean isSuccessful, String message, Object data) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.data = data;
    }
    //no need for setters
    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
