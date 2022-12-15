package com.example.restapi.services;

import com.example.restapi.dao.FileDAO;
import com.example.restapi.interfaces.IFileService;
import com.example.restapi.model.FileInfo;
import com.example.restapi.utils.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final FileDAO fileDAO;
    private final FileManager fileManager;

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public FileInfo upload(MultipartFile resource) throws IOException {
        String key = (String) generateKey(resource.getName());
        FileInfo createdFile = FileInfo.builder()
                .name(resource.getOriginalFilename())
                .key(key)
                .size(resource.getSize())
                .build();
        createdFile = fileDAO.create(createdFile);
        fileManager.upload(resource.getBytes(), key);

        return createdFile;
    }
}
