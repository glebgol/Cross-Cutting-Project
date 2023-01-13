package com.glebgol.restapi.controllers;

import builder.FileReaderBuilder;
import ciphers.KeyValidation;
import com.glebgol.restapi.responses.FileUploadResponse;
import com.glebgol.restapi.utils.constants.Constants;
import com.glebgol.restapi.utils.FileDeleteUtil;
import com.glebgol.restapi.utils.FileUploadUtil;
import interfaces.IFileReader;
import interfaces.IFileReaderBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class FileCalculationController {
    protected final String uploadPath = Constants.FILE_UPLOAD_PATH;
    protected final String downloadUri = Constants.DOWNLOAD_URI;

    @PostMapping("/calculate")
    public ResponseEntity<FileUploadResponse> calculate(@RequestParam("file") MultipartFile inputFile,
                                                        @RequestParam(value = "outputfile") String outputFilename,
                                                        @RequestParam(value = "iszipped", required = false) boolean isZipped,
                                                        @RequestParam(value="decryptionkeys", required = false) List<String> decryptionKeys,
                                                        @RequestParam(value = "extension") String extension) throws IOException {
        if (!KeyValidation.isValidForCalculation(decryptionKeys)) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadUtil.saveFile(uploadPath, inputFile);
        File file = null;
        try {
            IFileReaderBuilder builder = new FileReaderBuilder(extension, uploadPath + inputFile.getOriginalFilename());
            builder.setEncrypting(decryptionKeys);
            builder.setZipping(isZipped);
            IFileReader reader = builder.getFileReader();

            file = new File(uploadPath + outputFilename);
            reader.calculate(file);
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
