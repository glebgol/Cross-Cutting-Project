package com.glebgol.restapi.dto;

import com.glebgol.restapi.validation.EncryptionKey;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public final class EncryptionParamsDTO {
    @NotNull
    private MultipartFile inputFile;
    @NotBlank(message = "output file name can't be null or empty")
    private String outputFilename;
    @EncryptionKey(message = "Length of key should be 16!")
    private String key;
}
