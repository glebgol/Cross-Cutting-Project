package com.glebgol.restapi.utils.impl;

import com.glebgol.restapi.utils.FileDownloadUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            return new UrlResource(foundFile.toUri());
        }
        return null;
    }
}
