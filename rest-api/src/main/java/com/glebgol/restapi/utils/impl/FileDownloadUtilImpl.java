package com.glebgol.restapi.utils.impl;

import com.glebgol.restapi.utils.FileDownloadUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
@Component
public class FileDownloadUtilImpl implements FileDownloadUtil {
    private Path foundFile;

    @Override
    public Resource getFileAsResource(String fileName) throws IOException {
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().equals(fileName)) {
                foundFile = file;
            }
        });
        if (foundFile != null) {
            log.info("Download file : " + fileName);
            return new UrlResource(foundFile.toUri());
        }
        log.warn("File with name \"" + fileName + "\" not found");
        return null;
    }
}
