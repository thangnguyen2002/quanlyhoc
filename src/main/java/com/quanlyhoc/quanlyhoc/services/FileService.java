package com.quanlyhoc.quanlyhoc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        // Generate a unique identifier for the file
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.write(path, bytes);

        // Generate the file URL
        return uniqueFileName;
    }
}
