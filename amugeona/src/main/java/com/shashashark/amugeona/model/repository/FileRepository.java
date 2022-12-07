package com.shashashark.amugeona.model.repository;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.shashashark.amugeona.util.MultipartUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Repository
public class FileRepository {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public FileRepository(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public void store(String fullPath, MultipartFile multipartFile) {
        File file = new File(MultipartUtil.getLocalHomeDirectory(), fullPath);
        if (!file.exists()) {
            System.out.println(file.mkdirs());
        }
        try {
            multipartFile.transferTo(file);
            amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }

    }
}