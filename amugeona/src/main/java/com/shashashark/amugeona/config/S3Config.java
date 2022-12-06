package com.shashashark.amugeona.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.shashashark.amugeona.model.repository.FileRepository;
import com.shashashark.amugeona.model.service.FileUploadService;
import com.shashashark.amugeona.model.service.Impl.FileUploadServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public FileUploadService fileUploadService() {
        return new FileUploadServiceImpl(fileRepository());
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCredits = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredits))
                .build();
    }

    @Bean
    public FileRepository fileRepository() {
        return new FileRepository(amazonS3Client());
    }
}