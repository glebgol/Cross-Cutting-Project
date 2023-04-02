package com.glebgol.restapi.utils;


import org.springframework.web.multipart.MultipartFile;

public interface FileUploadUtil {
    void deleteFile(String path, String fileName);
    void saveFile(String uploadPathName, MultipartFile multipartFile);
}
