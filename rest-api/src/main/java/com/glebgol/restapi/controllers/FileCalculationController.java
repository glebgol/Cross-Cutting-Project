package com.glebgol.restapi.controllers;

import com.glebgol.businesslogic.builder.FileReaderBuilder;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IFileReaderBuilder;
import com.glebgol.businesslogic.utils.ciphers.KeyValidation;
import com.glebgol.restapi.dto.CalculationParamsDTO;
import com.glebgol.restapi.responses.FileUploadResponse;
import com.glebgol.restapi.utils.constants.Constants;
import com.glebgol.restapi.utils.FileDeleteUtil;
import com.glebgol.restapi.utils.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/")
public class FileCalculationController {
    protected final String uploadPath = Constants.FILE_UPLOAD_PATH;
    protected final String downloadUri = Constants.DOWNLOAD_URI;

    @PostMapping("/calculate")
    public ResponseEntity<FileUploadResponse> calculate(@Validated CalculationParamsDTO calculationParams) throws IOException {
        if (!KeyValidation.isValidForCalculation(calculationParams.getDecryptionKeys())) {
            return ResponseEntity.badRequest().build();
        }
        FileUploadUtil.saveFile(uploadPath, calculationParams.getInputFile());

        IFileReaderBuilder builder = new FileReaderBuilder(calculationParams.getExtension(), uploadPath + calculationParams.getInputFile().getOriginalFilename());
        if (calculationParams.getDecryptionKeys() != null) {
            builder.setEncrypting(calculationParams.getDecryptionKeys());
        }
        builder.setZipping(calculationParams.isZipped());
        IFileReader reader = builder.getFileReader();

        File file = new File(uploadPath + calculationParams.getOutputFilename());
        try {
            reader.calculate(file);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        } finally {
            FileDeleteUtil.deleteFile(uploadPath, calculationParams.getInputFile().getOriginalFilename());
        }
        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(calculationParams.getOutputFilename())
                .size(file.getTotalSpace())
                .downloadUri(downloadUri + calculationParams.getOutputFilename())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
