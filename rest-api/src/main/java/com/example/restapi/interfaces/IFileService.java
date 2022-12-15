package com.example.restapi.interfaces;

import com.example.restapi.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    FileInfo upload(MultipartFile resource) throws IOException;
}
