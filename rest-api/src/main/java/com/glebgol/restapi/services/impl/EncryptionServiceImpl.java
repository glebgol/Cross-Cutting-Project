package com.glebgol.restapi.services.impl;

import com.glebgol.businesslogic.contracts.exceptions.CryptoException;
import com.glebgol.businesslogic.utils.ciphers.CryptoUtils;
import com.glebgol.restapi.dto.EncryptionParamsDTO;
import com.glebgol.restapi.services.EncryptionService;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public File encrypt(EncryptionParamsDTO encryptionParamsDTO) {
        String encryptionKey = encryptionParamsDTO.getKey();
        String outputFilename = encryptionParamsDTO.getOutputFilename();
        String inputFilename = FILE_UPLOAD_PATH + encryptionParamsDTO.getInputFile().getOriginalFilename();
        File outputFile = new File(FILE_UPLOAD_PATH + outputFilename);

        try {
            CryptoUtils.encrypt(encryptionKey, inputFilename, outputFile.getAbsolutePath());
        } catch (CryptoException e) {
            throw new RuntimeException(e);
        }
        return outputFile;
    }

    @Override
    public File decrypt(EncryptionParamsDTO encryptionParamsDTO) {
        String encryptionKey = encryptionParamsDTO.getKey();
        String outputFilename = encryptionParamsDTO.getOutputFilename();
        String inputFilename = FILE_UPLOAD_PATH + encryptionParamsDTO.getInputFile().getOriginalFilename();
        File outputFile = new File(FILE_UPLOAD_PATH + outputFilename);

        try {
            CryptoUtils.decrypt(encryptionKey, inputFilename, outputFile.getAbsolutePath());
        } catch (CryptoException e) {
            throw new RuntimeException(e);
        }
        return outputFile;
    }
}
