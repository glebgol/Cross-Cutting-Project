package com.glebgol.restapi.services.impl;

import com.glebgol.businesslogic.builder.FileReaderBuilder;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IFileReaderBuilder;
import com.glebgol.restapi.dto.CalculationParamsDTO;
import com.glebgol.restapi.services.CalculationService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public File calculate(CalculationParamsDTO calculationParamsDTO) {
        String fileExtension = calculationParamsDTO.getExtension();
        List<String> encryptionKeys = calculationParamsDTO.getDecryptionKeys();
        boolean isZipped = calculationParamsDTO.getIsZipped();
        String uploadPath = FILE_UPLOAD_PATH + calculationParamsDTO.getInputFile().getOriginalFilename();
        String outputFilename = calculationParamsDTO.getOutputFilename();

        IFileReaderBuilder builder = new FileReaderBuilder(fileExtension, uploadPath);

        if (encryptionKeys != null) {
            builder.setEncrypting(encryptionKeys);
        }
        builder.setZipping(isZipped);

        IFileReader reader = builder.getFileReader();

        File file = new File(FILE_UPLOAD_PATH + outputFilename);

        try {
            reader.calculate(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
