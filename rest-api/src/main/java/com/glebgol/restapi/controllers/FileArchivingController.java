package com.glebgol.restapi.controllers;

import com.glebgol.businesslogic.utils.archivers.ArchivingFileManager;
import com.glebgol.restapi.responses.FileUploadResponse;
import com.glebgol.restapi.utils.constants.Constants;
import com.glebgol.restapi.utils.FileDeleteUtil;
import com.glebgol.restapi.utils.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequestMapping("api/v1/")
public class FileArchivingController {
    protected final String uploadPath = Constants.FILE_UPLOAD_PATH;
    protected final String downloadUri = Constants.DOWNLOAD_URI;

    @PostMapping("/zip")
    public ResponseEntity<FileUploadResponse> zip(@RequestParam(value= "file") MultipartFile inputFile) throws IOException {
        FileUploadUtil.saveFile(uploadPath, inputFile);
        String achieveFileName = ArchivingFileManager.getNameOfArchiveFile(inputFile.getOriginalFilename());
        File file = null;
        try {
            file = new File(uploadPath + achieveFileName);
            ArchivingFileManager.zipFile(uploadPath + inputFile.getOriginalFilename());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(uploadPath, inputFile.getOriginalFilename());
        }

        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(achieveFileName)
                .size(file.getTotalSpace())
                .downloadUri(downloadUri + achieveFileName)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/unzip")
    public ResponseEntity<FileUploadResponse> unZip(@RequestParam(value= "file") MultipartFile inputFile, @RequestParam(value= "outputfile") String outputFilename) throws IOException {
        FileUploadUtil.saveFile(uploadPath, inputFile);
        File file = null;
        try {
            file = new File(uploadPath + outputFilename);
            ArchivingFileManager.unZipFile(uploadPath + inputFile.getOriginalFilename(), uploadPath + outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(uploadPath, inputFile.getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(outputFilename)
                .size(file.getTotalSpace())
                .downloadUri(downloadUri + outputFilename)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
