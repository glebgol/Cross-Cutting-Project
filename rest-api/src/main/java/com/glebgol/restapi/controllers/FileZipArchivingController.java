package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.dto.UnzippingParamsDTO;
import com.glebgol.restapi.services.ZipArchivingService;
import com.glebgol.restapi.utils.InputFileResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

import static com.glebgol.restapi.utils.constants.Constants.DOWNLOAD_URI;
import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Log4j2
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileZipArchivingController {
    private final ZipArchivingService zipArchivingService;

    @PostMapping(value = "/zip", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileUploadResponse> zip(@RequestParam MultipartFile inputFile) {
        File file = null;
        try (var inputResource = new InputFileResource(FILE_UPLOAD_PATH, inputFile)) {
            file = zipArchivingService.zip(inputFile);
        } catch (Exception ex) {
            log.error("zip POST method Internal Server Error");
            return ResponseEntity.internalServerError().build();
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(file.getName())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + file.getName())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/unzip", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileUploadResponse> unzip(@Valid UnzippingParamsDTO unzippingParamsDTO) {
        File file = null;
        final MultipartFile inputFile = unzippingParamsDTO.getInputFile();
        final String outputFilename = unzippingParamsDTO.getOutputFilename();
        try (var inputResource = new InputFileResource(FILE_UPLOAD_PATH, unzippingParamsDTO)) {
            file = zipArchivingService.unzip(inputFile, outputFilename);
        } catch (Exception ex) {
            log.error("unzip POST method Internal Server Error");
            return ResponseEntity.internalServerError().build();
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(outputFilename)
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + outputFilename)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
