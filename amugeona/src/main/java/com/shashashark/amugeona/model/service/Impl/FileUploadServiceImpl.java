package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.FileDto;
import com.shashashark.amugeona.model.repository.FileRepository;
import com.shashashark.amugeona.model.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final FileRepository fileRepository;


    @Override
    public FileDto save(FileDto fileDto, MultipartFile multipartFile) {
        System.out.println(fileDto.getPath());
        fileRepository.store(fileDto.getPath(), multipartFile);
        return fileDto;
    }
}