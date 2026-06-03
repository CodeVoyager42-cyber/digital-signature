package com.example.digital_signature.dto.response;

public class CertificateResponse {
    private String alias;
    private String type;
    private String encoded;

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getEncoded() { return encoded; }
    public void setEncoded(String encoded) { this.encoded = encoded; }
}

