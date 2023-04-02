package com.glebgol.restapi.dto;

import com.glebgol.restapi.validation.DecryptionKeysForCalculation;
import com.glebgol.restapi.validation.FileExtension;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class CalculationParamsDTO extends ParamsDTO {
    private Boolean isZipped = false;
    @DecryptionKeysForCalculation(message = "not valid for calculation decryption keys")
    private List<String> decryptionKeys;
    @FileExtension
    private String extension;
}
