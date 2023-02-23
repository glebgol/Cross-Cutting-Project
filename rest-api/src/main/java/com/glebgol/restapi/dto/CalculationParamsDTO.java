package com.glebgol.restapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class CalculationParamsDTO {
    @NotNull
    private MultipartFile inputFile;
    @NotNull
    private String outputFilename;
    private Boolean isZipped;
    private List<String> decryptionKeys;
    @NotNull
    private String extension;
}
