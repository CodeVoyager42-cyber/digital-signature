package com.example.digital_signature.service;

import com.example.digital_signature.dto.response.CertificateResponse;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
public class CertificateService {

    private final TokenService tokenService;

    public CertificateService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public List<CertificateResponse> getCertificates() {
        try {
            KeyStore ks = tokenService.getKeyStore();

            List<CertificateResponse> list = new ArrayList<>();

            Enumeration<String> aliases = ks.aliases();

            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();

                Certificate cert = ks.getCertificate(alias);

                if (cert != null) {
                    CertificateResponse res = new CertificateResponse();
                    res.setAlias(alias);
                    res.setType(cert.getType());
                    res.setEncoded(cert.toString());

                    list.add(res);
                }
            }

            return list;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read certificates: " + e.getMessage());
        }
    }
}