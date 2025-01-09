package com.example.demo.controller;

import com.example.demo.dto.FileUploadDTO;
import com.example.demo.service.MinioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {
    private final MinioService minioService;

    public FileController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public String uploadFile(FileUploadDTO fileUploadDTO) {
        try {
            var fileName = minioService.uploadMusicSheetFile(fileUploadDTO.getFile(), fileUploadDTO.getMusicId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
