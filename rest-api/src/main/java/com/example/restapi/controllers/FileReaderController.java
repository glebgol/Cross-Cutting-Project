package com.example.restapi.controllers;

import archivers.ArchivingFileManager;
import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import ciphers.KeyValidation;
import com.example.restapi.responses.*;
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

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri(DOWNLOAD_URI + fileName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/calculate")
    public ResponseEntity<FileUploadResponse> calculate(@RequestParam("file") MultipartFile inputFile,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(value = "iszipped", required = false) boolean isZipped,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys,
                                            @RequestParam(value = "extension") String extension) throws IOException {
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, inputFile);

        if (decryptionKeys != null && !KeyValidation.isValidDecryptionKeys(decryptionKeys)) {
            return ResponseEntity.badRequest().build();
        }
        File file = null;
        try {
            IFileReaderBuilder builder = new FileReaderBuilder(extension, inputFile.getOriginalFilename());
            builder.setEncrypting(decryptionKeys);
            builder.setZipping(isZipped);

            file = new File(FILE_UPLOAD_PATH + outputFilename);

            IFileReader reader = builder.getFileReader();
            reader.getResult(file.getAbsolutePath());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(FILE_UPLOAD_PATH + outputFilename);
        response.setSize(file.getTotalSpace());
        response.setDownloadUri(DOWNLOAD_URI + outputFilename);

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
            CryptoUtils.encrypt(key, inputFile.getOriginalFilename(), file.getAbsolutePath());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(FILE_UPLOAD_PATH + outputFilename);
        response.setSize(file.getTotalSpace());
        response.setDownloadUri(DOWNLOAD_URI + outputFilename);
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
            CryptoUtils.decrypt(key, inputFile.getOriginalFilename(), file.getAbsolutePath());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(FILE_UPLOAD_PATH + outputFilename);
        response.setSize(file.getTotalSpace());
        response.setDownloadUri(DOWNLOAD_URI + outputFilename);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("zip/")
    public ResponseEntity<ZipResponse> zip(@RequestParam(value= "inputfile") String inputFilename) {
        try {
            ArchivingFileManager.zipFile(inputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ZipResponse(inputFilename));
    }

    @GetMapping("/unzip")
    public ResponseEntity<UnzipResponse> unZip(@RequestParam(value= "inputfile") String inputFilename, @RequestParam(value= "outputfile") String outputFilename) {
        try {
            ArchivingFileManager.unZipFile(inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new UnzipResponse(inputFilename, outputFilename));
    }
}
