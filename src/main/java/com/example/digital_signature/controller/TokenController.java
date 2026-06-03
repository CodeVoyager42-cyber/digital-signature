package com.example.digital_signature.controller;

import com.example.digital_signature.dto.request.PinLoginRequest;
import com.example.digital_signature.dto.response.TokenStatusResponse;
import com.example.digital_signature.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PinLoginRequest request) {
        if (request == null || request.getPin() == null || request.getPin().isEmpty()) {
            throw new IllegalArgumentException("PIN is required");
        }
        tokenService.login(request.getPin());
        return ResponseEntity.ok("PIN accepted, token unlocked");
    }

    @GetMapping("/status")
    public ResponseEntity<TokenStatusResponse> status() {
        TokenStatusResponse response = new TokenStatusResponse();
        response.setConnected(tokenService.isLoggedIn());
        response.setMessage(tokenService.isLoggedIn() ? "Token unlocked" : "Token locked");

        return ResponseEntity.ok(response);
    }
}