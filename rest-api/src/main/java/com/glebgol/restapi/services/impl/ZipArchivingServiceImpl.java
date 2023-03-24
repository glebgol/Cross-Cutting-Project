package com.glebgol.restapi.services.impl;

import com.glebgol.businesslogic.utils.archivers.ArchivingFileManager;
import com.glebgol.restapi.services.ZipArchivingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Service
public class ZipArchivingServiceImpl implements ZipArchivingService {
    @Override
    public File zip(MultipartFile inputFile) {
        final String inputFilename = inputFile.getOriginalFilename();
        String achieveFileName = ArchivingFileManager.getNameOfArchiveFile(inputFilename);
        File file = new File(FILE_UPLOAD_PATH + achieveFileName);

        try {
            ArchivingFileManager.zipFile(FILE_UPLOAD_PATH + inputFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Override
    public File unzip(MultipartFile inputFile, String outputFilename) {
        final String inputFilename = inputFile.getOriginalFilename();
        File file = new File(FILE_UPLOAD_PATH + outputFilename);

        try {
            ArchivingFileManager.unZipFile(FILE_UPLOAD_PATH + inputFilename, FILE_UPLOAD_PATH + outputFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
