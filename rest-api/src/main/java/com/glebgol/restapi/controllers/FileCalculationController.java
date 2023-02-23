package com.glebgol.restapi.controllers;

import com.glebgol.businesslogic.utils.ciphers.KeyValidation;
import com.glebgol.restapi.dto.CalculationParamsDTO;
import com.glebgol.restapi.responses.FileUploadResponse;
import com.glebgol.restapi.services.CalculationService;
import com.glebgol.restapi.utils.FileDeleteUtil;
import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import static com.glebgol.restapi.utils.constants.Constants.DOWNLOAD_URI;
import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileCalculationController {
    private final CalculationService calculationService;
    @PostMapping("/calculate")
    public ResponseEntity<FileUploadResponse> calculate(@Validated CalculationParamsDTO calculationParams) {
        if (!KeyValidation.isValidForCalculation(calculationParams.getDecryptionKeys())) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadUtil.saveFile(FILE_UPLOAD_PATH, calculationParams.getInputFile());

        File file = null;
        try {
            file = calculationService.calculate(calculationParams);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(DOWNLOAD_URI, calculationParams.getInputFile().getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(calculationParams.getOutputFilename())
                .size(file.getTotalSpace())
                .downloadUri(DOWNLOAD_URI + calculationParams.getOutputFilename())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
