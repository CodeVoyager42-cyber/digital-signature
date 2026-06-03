package com.example.digital_signature.dto.request;

public class SignRequest {
    private String alias;
    private String data;

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}