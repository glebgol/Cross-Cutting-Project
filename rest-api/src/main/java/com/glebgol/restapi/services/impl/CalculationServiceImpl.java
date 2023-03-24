package com.glebgol.restapi.services.impl;

import com.glebgol.businesslogic.builder.FileReaderBuilder;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IFileReaderBuilder;
import com.glebgol.restapi.dto.CalculationParamsDTO;
import com.glebgol.restapi.services.CalculationService;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public File calculate(CalculationParamsDTO calculationParamsDTO) {
        IFileReaderBuilder builder = new FileReaderBuilder(calculationParamsDTO.getExtension(),
                FILE_UPLOAD_PATH + calculationParamsDTO.getInputFile().getOriginalFilename());

        if (calculationParamsDTO.getDecryptionKeys() != null) {
            builder.setEncrypting(calculationParamsDTO.getDecryptionKeys());
        }
        builder.setZipping(calculationParamsDTO.getIsZipped());
        IFileReader reader = builder.getFileReader();

        File file = new File(FILE_UPLOAD_PATH + calculationParamsDTO.getOutputFilename());

        try {
            reader.calculate(file);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return file;
    }
}
