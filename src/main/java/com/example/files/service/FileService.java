package com.example.files.service;

import com.example.files.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface FileService {

    FileEntity upload(MultipartFile file) throws IOException;
    Optional<FileEntity> getFileById(UUID id) throws FileNotFoundException;
}
