package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileDto save(FileDto fileDto, MultipartFile multipartFile);
}
