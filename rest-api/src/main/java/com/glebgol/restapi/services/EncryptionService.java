package com.glebgol.restapi.services;

import com.glebgol.restapi.dto.EncryptionParamsDTO;

import java.io.File;

public interface EncryptionService {
    File encrypt(EncryptionParamsDTO encryptionParamsDTO);
    File decrypt(EncryptionParamsDTO encryptionParamsDTO);
}
