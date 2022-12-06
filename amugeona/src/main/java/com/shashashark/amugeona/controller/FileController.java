package com.shashashark.amugeona.controller;

import com.shashashark.amugeona.model.dto.FileDto;
import com.shashashark.amugeona.model.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/file", produces = APPLICATION_JSON_VALUE)
@CrossOrigin
public class FileController {
    private final FileUploadService fileUploadService;

    public FileController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile multipartFile, String filePath) {
        FileDto fileDto = FileDto.multipartOf(multipartFile, filePath);
        fileUploadService.save(fileDto, multipartFile);
        String url = "https://final-ssafit.s3.ap-northeast-2.amazonaws.com/" + fileDto.getPath();
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

}
