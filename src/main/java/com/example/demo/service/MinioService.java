package com.example.demo.service;

import com.example.demo.entity.MusicSheet;
import com.example.demo.mapper.MusicMapper;
import com.example.demo.mapper.MusicSheetMapper;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class MinioService {
    private final MinioClient minioClient;
    private final MusicMapper musicMapper;
    private final MusicSheetMapper musicSheetMapper;

    @Value("${minio.bucketName}")
    private String bucketName;

    public MinioService(MinioClient minioClient, MusicMapper musicMapper, MusicSheetMapper musicSheetMapper) {
        this.minioClient = minioClient;
        this.musicMapper = musicMapper;
        this.musicSheetMapper = musicSheetMapper;
    }

    public String uploadMusicSheetFile(MultipartFile file, Long musicId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        var musicSheet = new MusicSheet();
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType()).build());
            String fileUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET).bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            var music = musicMapper.selectById(musicId);
            musicSheet.setMusic(music);
            musicSheet.setMusicId(musicId);
            musicSheet.setUrl(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        musicSheetMapper.insert(musicSheet);
        return musicSheet.getUrl();
    }
}
