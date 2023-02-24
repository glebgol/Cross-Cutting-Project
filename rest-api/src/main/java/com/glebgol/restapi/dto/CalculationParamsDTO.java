package com.glebgol.restapi.dto;

import com.glebgol.restapi.validation.DecryptionKeysForCalculation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class CalculationParamsDTO {
    @NotNull(message = "input file can't be null")
    private MultipartFile inputFile;
    @NotBlank(message = "output file name can't be null or empty")
    private String outputFilename;
    private Boolean isZipped;
    @DecryptionKeysForCalculation(message = "fuck")
    private List<String> decryptionKeys;
    @Pattern(regexp = "txt|xml|json", message = "Calculation supports only txt|xml|json extensions")
    private String extension;
}
