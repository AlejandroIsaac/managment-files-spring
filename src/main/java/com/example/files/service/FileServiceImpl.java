package com.example.files.service;

import com.example.files.entity.FileEntity;
import com.example.files.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    FileRepository fileRepository;

    @Override
    public FileEntity upload(MultipartFile file) throws IOException {
        String name= StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = FileEntity.builder()
                .name(name)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        return fileRepository.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getFileById(UUID id) throws FileNotFoundException {
        Optional<FileEntity> file = fileRepository.findById(id);
        if(file.isPresent()){
            return file;
        }throw new FileNotFoundException();
    }
}
