package com.example.digital_signature.service;

import com.example.digital_signature.config.PKCS11Config;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.Provider;

@Service
public class TokenService {

    private KeyStore keyStore;
    private boolean loggedIn = false;

    public void login(String pin) {
        try {
            if (pin == null || pin.isEmpty()) {
                throw new IllegalArgumentException("PIN cannot be null or empty");
            }

            Provider provider = PKCS11Config.getProvider();

            keyStore = KeyStore.getInstance("PKCS11", provider);

            // 🔐 PIN unlocks smart card session
            keyStore.load(null, pin.toCharArray());

            loggedIn = true;

        } catch (Exception e) {
            loggedIn = false;
            throw new RuntimeException("PIN incorrect or token error: " + e.getMessage());
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public KeyStore getKeyStore() {
        if (!loggedIn) {
            throw new RuntimeException("Token not unlocked. Call /api/token/login first.");
        }
        return keyStore;
    }
}