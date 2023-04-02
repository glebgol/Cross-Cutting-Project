package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.EncryptionParamsDTO;
import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.services.EncryptionService;
import com.glebgol.restapi.utils.FileDeleteUtil;
import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileEncryptionController {
    private final EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<?> encrypt(@Valid EncryptionParamsDTO encryptionParamsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile());
        File file = null;
        try {
            file = encryptionService.encrypt(encryptionParamsDTO);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile().getOriginalFilename());
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
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile());
        File file = null;
        try {
            file = encryptionService.decrypt(encryptionParamsDTO);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(FILE_UPLOAD_PATH, encryptionParamsDTO.getInputFile().getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(encryptionParamsDTO.getOutputFilename())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + encryptionParamsDTO.getOutputFilename())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
