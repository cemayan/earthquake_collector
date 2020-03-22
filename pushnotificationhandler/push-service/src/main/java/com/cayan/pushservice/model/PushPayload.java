package com.cayan.pushservice.model;

public class PushPayload {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PushPayload(String message, String userId, String token) {
        this.message = message;
        this.userId = userId;
        this.token = token;
    }

    public String message;
    public String userId;
    public String token;
}
