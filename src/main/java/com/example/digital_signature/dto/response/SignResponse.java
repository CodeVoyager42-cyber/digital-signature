package com.example.digital_signature.dto.response;

public class SignResponse {
    private String signature;
    private String signer;

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }

    public String getSigner() { return signer; }
    public void setSigner(String signer) { this.signer = signer; }
}