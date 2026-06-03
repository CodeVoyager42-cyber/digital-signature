package com.example.digital_signature.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class DocumentService {

    private static final String UPLOAD_DIR = "uploads";

    public DocumentService() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory: " + e.getMessage());
        }
    }

    public String store(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File cannot be null or empty");
            }

            if (file.getSize() > 10_000_000) { // 10MB limit
                throw new IllegalArgumentException("File size exceeds 10MB limit");
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);

            Files.write(filePath, file.getBytes());

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }
}

