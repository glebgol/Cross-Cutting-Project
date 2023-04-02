package com.glebgol.restapi.dto;

import com.glebgol.restapi.validation.EncryptionKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class EncryptionParamsDTO extends ParamsDTO {
    @EncryptionKey(message = "Length of key should be 16!")
    private String key;
}
