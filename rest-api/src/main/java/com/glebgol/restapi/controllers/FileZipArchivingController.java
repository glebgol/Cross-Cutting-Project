package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.services.ZipArchivingService;
import com.glebgol.restapi.utils.FileDeleteUtil;
import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.glebgol.restapi.utils.constants.Constants.DOWNLOAD_URI;
import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileZipArchivingController {
    private final ZipArchivingService zipArchivingService;

    @PostMapping(value = "/zip", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileUploadResponse> zip(@RequestParam MultipartFile inputFile) {
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        File file = null;
        try {
            file = zipArchivingService.zip(inputFile);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(FILE_UPLOAD_PATH, inputFile.getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(file.getName())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + file.getName())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/unzip", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileUploadResponse> unzip(@RequestParam MultipartFile inputFile, @RequestParam String outputFilename) {
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        File file = null;
        try {
            file = zipArchivingService.unzip(inputFile, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(FILE_UPLOAD_PATH, inputFile.getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(outputFilename)
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + outputFilename)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
