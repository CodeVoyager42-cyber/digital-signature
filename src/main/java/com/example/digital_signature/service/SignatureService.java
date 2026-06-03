package com.example.digital_signature.service;

import com.example.digital_signature.dto.request.SignRequest;
import com.example.digital_signature.dto.response.SignResponse;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

@Service
public class SignatureService {

    private final TokenService tokenService;

    public SignatureService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public SignResponse sign(SignRequest request) {

        try {
            KeyStore ks = tokenService.getKeyStore();

            String alias = request.getAlias();

            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, null);
            Certificate cert = ks.getCertificate(alias);

            Signature signature = Signature.getInstance("SHA256withRSA");

            signature.initSign(privateKey);
            signature.update(request.getData().getBytes());

            byte[] signed = signature.sign();

            SignResponse res = new SignResponse();
            res.setSignature(Base64.getEncoder().encodeToString(signed));
            res.setSigner(cert.toString());

            return res;

        } catch (Exception e) {
            throw new RuntimeException("Signing failed: " + e.getMessage());
        }
    }

    public boolean verify(String data, byte[] signatureBytes, PublicKey publicKey) {
        try {
            if (data == null || signatureBytes == null || publicKey == null) {
                throw new IllegalArgumentException("Data, signature bytes, and public key cannot be null");
            }

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(data.getBytes());

            return signature.verify(signatureBytes);

        } catch (Exception e) {
            throw new RuntimeException("Verification failed: " + e.getMessage());
        }
    }
}