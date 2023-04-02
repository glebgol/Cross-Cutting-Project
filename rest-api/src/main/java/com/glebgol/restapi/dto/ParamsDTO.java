package com.glebgol.restapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ParamsDTO {
    @NotNull(message = "input file can't be null")
    private MultipartFile inputFile;
    @NotBlank(message = "output file name can't be null or empty")
    private String outputFilename;
}
