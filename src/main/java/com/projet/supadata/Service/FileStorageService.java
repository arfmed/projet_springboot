package com.projet.supadata.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class FileStorageService {
    private final String baseDir = "C:/pfe/frontend/src/assets/uploads/";

    public String save(MultipartFile file, String folder) throws IOException {
        if (file == null || file.isEmpty()) return null;

        Path dirPath = Paths.get(baseDir + folder);
        Files.createDirectories(dirPath);

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = dirPath.resolve(filename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return  filename;
    }
}
