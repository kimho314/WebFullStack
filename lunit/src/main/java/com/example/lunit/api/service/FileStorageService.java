package com.example.lunit.api.service;

import com.example.lunit.common.exception.ServiceException;
import com.example.lunit.common.properties.StorageProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path rootLocation;

    @Autowired
    public FileStorageService(StorageProperties storageProperties) {
        if (storageProperties.getLocation().trim().isEmpty()) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "could not initialize storage");
        }
    }

    public Path store(MultipartFile file) {
        try {
            if (file.isEmpty() || !StringUtils.hasText(file.getOriginalFilename())) {
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to store empty file.");
            }

            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return destinationFile;
        }
        catch (IOException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
