package com.example.files.controller;

import com.example.files.entity.FileEntity;
import com.example.files.response.ResponseMessage;
import com.example.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    ResponseEntity<ResponseMessage> save(@RequestParam("file") MultipartFile file) throws Exception{
        fileService.upload(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("Se guardo exitosamente"));
    }

    @GetMapping("/get/{id}")
    ResponseEntity<byte[]> getById(@PathVariable UUID id) throws FileNotFoundException {
        FileEntity file= fileService.getFileById(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, file.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName()+"\"")
                .body(file.getData());
    }
}
