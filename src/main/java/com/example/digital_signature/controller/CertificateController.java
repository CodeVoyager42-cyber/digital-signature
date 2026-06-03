package com.example.digital_signature.controller;

import com.example.digital_signature.dto.response.CertificateResponse;
import com.example.digital_signature.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<CertificateResponse>> getCertificates() {
        try {
            return ResponseEntity.ok(certificateService.getCertificates());
        } catch (RuntimeException e) {
            throw new IllegalStateException("Unable to fetch certificates: " + e.getMessage());
        }
    }
}