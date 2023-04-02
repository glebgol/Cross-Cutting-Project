package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.CalculationParamsDTO;
import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.services.CalculationService;
import com.glebgol.restapi.utils.InputFileResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import static com.glebgol.restapi.utils.constants.Constants.DOWNLOAD_URI;
import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Log4j2
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileCalculationController {
    private final CalculationService calculationService;

    @PostMapping(value = "/calculate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> calculate(@Valid CalculationParamsDTO calculationParams, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Calculate POST method Bad Request");
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        File file = null;
        try (var inputFileResource = new InputFileResource(FILE_UPLOAD_PATH, calculationParams)) {
            file = calculationService.calculate(calculationParams);
        } catch (Exception ex) {
            log.error("Calculate POST method Internal Server Error");
            return ResponseEntity.internalServerError().build();
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(calculationParams.getOutputFilename())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + calculationParams.getOutputFilename())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
