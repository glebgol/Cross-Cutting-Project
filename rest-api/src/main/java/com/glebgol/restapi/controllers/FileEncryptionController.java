package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.EncryptionParamsDTO;
import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.services.EncryptionService;
import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;

import static com.glebgol.restapi.utils.constants.Constants.DOWNLOAD_URI;
import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Log4j2
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileEncryptionController {
    private final EncryptionService encryptionService;
    private final FileUploadUtil fileUploadUtil;

    @PostMapping(value = "/encrypt", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> encrypt(@Valid EncryptionParamsDTO encryptionParamsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Encrypt POST method Bad Request");
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        fileUploadUtil.saveFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile());
        File file = null;
        try {
            file = encryptionService.encrypt(encryptionParamsDTO);
        } catch (Exception ex) {
            log.error("Encrypt POST method Internal Server Error");
            return ResponseEntity.internalServerError().build();
        } finally {
            fileUploadUtil.deleteFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile().getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(encryptionParamsDTO.getOutputFilename())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + encryptionParamsDTO.getOutputFilename())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/decrypt", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> decrypt(@Valid EncryptionParamsDTO encryptionParamsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        fileUploadUtil.saveFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile());
        File file = null;
        try {
            file = encryptionService.decrypt(encryptionParamsDTO);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            fileUploadUtil.deleteFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile().getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(encryptionParamsDTO.getOutputFilename())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + encryptionParamsDTO.getOutputFilename())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
