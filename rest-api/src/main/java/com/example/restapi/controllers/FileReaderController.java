package com.example.restapi.controllers;

import archivers.ArchivingFileManager;
import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import ciphers.KeyValidation;
import com.example.restapi.responses.*;
import com.example.restapi.utils.FileDeleteUtil;
import com.example.restapi.utils.FileUploadUtil;
import interfaces.IFileReader;
import interfaces.IFileReaderBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/file-reader/")
public class FileReaderController {
    private final String FILE_UPLOAD_PATH = "Files-Upload/";
    private final String DOWNLOAD_URI = "/downloadFile/";

    @PostMapping("/calculate")
    public ResponseEntity<FileUploadResponse> calculate(@RequestParam("file") MultipartFile inputFile,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(value = "iszipped", required = false) boolean isZipped,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys,
                                            @RequestParam(value = "extension") String extension) throws IOException {
        if (!KeyValidation.isValidForCalculation(decryptionKeys)) {
            return ResponseEntity.badRequest().build();
        }

        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        File file = null;
        try {
            IFileReaderBuilder builder = new FileReaderBuilder(extension, FILE_UPLOAD_PATH + inputFile.getOriginalFilename());
            builder.setEncrypting(decryptionKeys);
            builder.setZipping(isZipped);
            IFileReader reader = builder.getFileReader();

            file = new File(FILE_UPLOAD_PATH + outputFilename);
            reader.calculate(file);
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/encrypt")
    public ResponseEntity<FileUploadResponse> encrypt(@RequestParam(value= "file") MultipartFile inputFile,
                                                   @RequestParam(value = "outputfile") String outputFilename,
                                                   @RequestParam(value="key") String key) throws IOException {
        if (!KeyValidation.isValidDecryptionKey(key)) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        File file = null;
        try {
            file = new File(FILE_UPLOAD_PATH + outputFilename);
            CryptoUtils.encrypt(key, FILE_UPLOAD_PATH + inputFile.getOriginalFilename(), file.getAbsolutePath());
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<FileUploadResponse> decrypt(@RequestParam(value= "file") MultipartFile inputFile,
                                                   @RequestParam(value = "outputfile") String outputFilename,
                                                   @RequestParam(value="key") String key) throws IOException {
        if (!KeyValidation.isValidDecryptionKey(key)) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        File file = null;
        try {
            file = new File(FILE_UPLOAD_PATH + outputFilename);
            CryptoUtils.decrypt(key, FILE_UPLOAD_PATH + inputFile.getOriginalFilename(), file.getAbsolutePath());
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/zip")
    public ResponseEntity<FileUploadResponse> zip(@RequestParam(value= "file") MultipartFile inputFile) throws IOException {
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        String achieveFileName = ArchivingFileManager.getNameOfArchiveFile(inputFile.getOriginalFilename());
        File file = null;
        try {
            file = new File(FILE_UPLOAD_PATH + achieveFileName);
            ArchivingFileManager.zipFile(FILE_UPLOAD_PATH + inputFile.getOriginalFilename());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(FILE_UPLOAD_PATH, inputFile.getOriginalFilename());
        }

        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(achieveFileName)
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + achieveFileName)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/unzip")
    public ResponseEntity<FileUploadResponse> unZip(@RequestParam(value= "file") MultipartFile inputFile, @RequestParam(value= "outputfile") String outputFilename) throws IOException {
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);
        File file = null;
        try {
            file = new File(FILE_UPLOAD_PATH + outputFilename);
            ArchivingFileManager.unZipFile(FILE_UPLOAD_PATH + inputFile.getOriginalFilename(), FILE_UPLOAD_PATH + outputFilename);
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, multipartFile);

        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(fileName)
                .size(multipartFile.getSize())
                .downloadUri(DOWNLOAD_URI + fileName)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
