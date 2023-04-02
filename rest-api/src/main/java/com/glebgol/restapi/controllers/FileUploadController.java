package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.glebgol.restapi.utils.constants.Constants.DOWNLOAD_URI;
import static com.glebgol.restapi.utils.constants.Constants.FILE_UPLOAD_PATH;

@Log4j2
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadUtil fileUploadUtil;

    @PostMapping(value = "/uploadFile", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            log.warn("Multipart file is empty");
            return ResponseEntity.badRequest().build();
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        fileUploadUtil.saveFile(FILE_UPLOAD_PATH, multipartFile);

        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(fileName)
                .size(multipartFile.getSize())
                .downloadUri(DOWNLOAD_URI + fileName)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
