package com.glebgol.restapi.services;

import com.glebgol.businesslogic.utils.archivers.ArchivingFileManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Service
public class ZipArchivingServiceImpl implements ZipArchivingService {
    @Override
    public File zip(MultipartFile inputFile) {
        String achieveFileName = ArchivingFileManager.getNameOfArchiveFile(inputFile.getOriginalFilename());
        File file = new File(FILE_UPLOAD_PATH + achieveFileName);
        try {
            ArchivingFileManager.zipFile(FILE_UPLOAD_PATH + inputFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Override
    public File unzip(MultipartFile inputFile, String outputFilename) {
        File file = new File(FILE_UPLOAD_PATH + outputFilename);
        try {
            ArchivingFileManager.unZipFile(FILE_UPLOAD_PATH + inputFile.getOriginalFilename(), FILE_UPLOAD_PATH + outputFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
