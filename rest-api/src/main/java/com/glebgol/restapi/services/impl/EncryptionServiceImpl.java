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
        final File file = new File(FILE_UPLOAD_PATH + encryptionParamsDTO.getOutputFilename());
        final String filename = FILE_UPLOAD_PATH + encryptionParamsDTO.getInputFile().getOriginalFilename();
        try {
            CryptoUtils.encrypt(encryptionParamsDTO.getKey(), filename, file.getAbsolutePath());
        } catch (CryptoException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Override
    public File decrypt(EncryptionParamsDTO encryptionParamsDTO) {
        final File file = new File(FILE_UPLOAD_PATH + encryptionParamsDTO.getOutputFilename());
        final String filename = FILE_UPLOAD_PATH + encryptionParamsDTO.getInputFile().getOriginalFilename();
        try {
            CryptoUtils.decrypt(encryptionParamsDTO.getKey(), filename, file.getAbsolutePath());
        } catch (CryptoException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
