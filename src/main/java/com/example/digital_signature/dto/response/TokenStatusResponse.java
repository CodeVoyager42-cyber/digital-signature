package com.example.digital_signature.dto.response;

public class TokenStatusResponse {
    private boolean connected;
    private String message;

    public boolean isConnected() { return connected; }
    public void setConnected(boolean connected) { this.connected = connected; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

