package com.glebgol.restapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class EncryptionParamsDTO {
    @NotNull
    private MultipartFile inputFile;
    @NotNull
    private String outputFilename;
    @NotNull
    private String key;
}
