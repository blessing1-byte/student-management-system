package org.example.registration.util;

public class Response {
    private Boolean isSuccessful;
private String message;
private Object data;

    public Response(Boolean isSuccessful, String message, Object data) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.data = data;
    }
}
