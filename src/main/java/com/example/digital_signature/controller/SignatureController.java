package com.example.digital_signature.controller;

import com.example.digital_signature.dto.request.SignRequest;
import com.example.digital_signature.dto.request.VerifyRequest;
import com.example.digital_signature.dto.response.SignResponse;
import com.example.digital_signature.service.SignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signatures")
public class SignatureController {

    private final SignatureService signatureService;

    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @PostMapping("/sign")
    public ResponseEntity<SignResponse> sign(@RequestBody SignRequest request) {
        if (request == null || request.getAlias() == null || request.getData() == null) {
            throw new IllegalArgumentException("Request with valid alias and data is required");
        }
        return ResponseEntity.ok(signatureService.sign(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestBody VerifyRequest request) {
        if (request == null || request.getData() == null || request.getSignatureBytes() == null || request.getPublicKey() == null) {
            throw new IllegalArgumentException("Request with valid data, signature bytes, and public key is required");
        }
        return ResponseEntity.ok(signatureService.verify(request.getData(), request.getSignatureBytes(), request.getPublicKey()));
    }
}