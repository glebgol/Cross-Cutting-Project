package com.glebgol.restapi.utils.impl;

import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Log4j2
@Component
public class FileUploadUtilImpl implements FileUploadUtil {

    @Override
    public void deleteFile(String path, String fileName) {
        File file = new File(path + "\\" + fileName);
        file.delete();
        log.info("Delete file : \"" + fileName + "\"");
    }

    @Override
    public void saveFile(String uploadPathName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(uploadPathName);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                log.error("Exception while creating directory");
                throw new RuntimeException(e);
            }
        }

        String fileName = multipartFile.getOriginalFilename();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Exception while uploading file");
            throw new RuntimeException(e);
        }
        log.info("File successfully has been uploaded");
    }
}
