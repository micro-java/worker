package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDTO {

    private Long userId;
    private Long musicId;
    private MultipartFile file;
}
