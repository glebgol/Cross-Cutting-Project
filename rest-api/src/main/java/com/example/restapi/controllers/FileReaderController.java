package com.example.restapi.controllers;

import archivers.ArchivingFileManager;
import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import ciphers.KeyValidation;
import com.example.restapi.responses.*;
import com.example.restapi.utils.FileUploadUtil;
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
    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + fileName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/calculate")
    public ResponseEntity<FileUploadResponse> calculate(@RequestParam("file") MultipartFile inputFile,
                            @RequestParam(value = "outputfile") String outputFilename,
                            @RequestParam(value = "iszipped", required = false) boolean isZipped,
                            @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys,
                                            @RequestParam(value = "extension") String extension) throws IOException {
        FileUploadUtil.saveFile(inputFile.getOriginalFilename(), inputFile);

        if (decryptionKeys != null && !KeyValidation.isValidDecryptionKeys(decryptionKeys)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            var readerBuilder = new FileReaderBuilder(extension, inputFile.getOriginalFilename());
            readerBuilder.setEncrypting(decryptionKeys);
            readerBuilder.setZipping(isZipped);

            File file = new File("Files-Upload/" + outputFilename);
            var reader = readerBuilder.getFileReader();
            reader.getResult(file.getAbsolutePath());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName("Files-Upload/" + outputFilename);
        //response.setSize(inputFile.getSize());
        response.setDownloadUri("/downloadFile/" + outputFilename);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("encrypt/")
    public ResponseEntity<FileUploadResponse> encrypt(@RequestParam(value= "inputfile") String inputFilename,
                                                   @RequestParam(value = "outputfile") String outputFilename,
                                                   @RequestParam(value="key") String key) {
        if (!KeyValidation.isValidDecryptionKey(key)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CryptoUtils.encrypt(key, inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName("Files-Upload/" + outputFilename);
        //response.setSize(inputFile.getSize());
        response.setDownloadUri("/downloadFile/" + outputFilename);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("decrypt/")
    public ResponseEntity<FileUploadResponse> decrypt(@RequestParam(value= "inputfile") String inputFilename,
                                                   @RequestParam(value = "outputfile") String outputFilename,
                                                   @RequestParam(value="key") String key) {
        if (!KeyValidation.isValidDecryptionKey(key)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CryptoUtils.decrypt(key, inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName("Files-Upload/" + outputFilename);
        //response.setSize(inputFile.getSize());
        response.setDownloadUri("/downloadFile/" + outputFilename);
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

    @GetMapping("unzip/")
    public ResponseEntity<UnzipResponse> unZip(@RequestParam(value= "inputfile") String inputFilename, @RequestParam(value= "outputfile") String outputFilename) {
        try {
            ArchivingFileManager.unZipFile(inputFilename, outputFilename);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new UnzipResponse(inputFilename, outputFilename));
    }
}
