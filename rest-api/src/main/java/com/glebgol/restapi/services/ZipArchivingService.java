package com.glebgol.restapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ZipArchivingService {
    File zip(MultipartFile inputFile);
    File unzip(MultipartFile inputFile, String outputFilename);
}
