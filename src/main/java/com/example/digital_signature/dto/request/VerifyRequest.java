package com.example.digital_signature.dto.request;

import java.security.PublicKey;

public class VerifyRequest {
    private String data;
    private byte[] signatureBytes;
    private PublicKey publicKey;

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public byte[] getSignatureBytes() { return signatureBytes; }
    public void setSignatureBytes(byte[] signatureBytes) { this.signatureBytes = signatureBytes; }

    public PublicKey getPublicKey() { return publicKey; }
    public void setPublicKey(PublicKey publicKey) { this.publicKey = publicKey; }
}

