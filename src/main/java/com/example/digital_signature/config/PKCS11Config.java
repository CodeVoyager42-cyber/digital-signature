package com.example.digital_signature.config;

import java.nio.file.Paths;
import java.security.Provider;
import java.security.Security;

public class PKCS11Config {

    private static Provider provider;

    public static Provider getProvider() {
        if (provider == null) {
            try {
                String path = Paths.get("pkcs11.cfg")
                        .toAbsolutePath()
                        .toString();

                System.out.println("PKCS11 config path: " + path);

                provider = Security.getProvider("SunPKCS11")
                        .configure(path);

                Security.addProvider(provider);

            } catch (Exception e) {
                throw new RuntimeException("Failed to load PKCS11 config", e);
            }
        }
        return provider;
    }
}