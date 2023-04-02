package com.glebgol.restapi.controllers;

import com.glebgol.restapi.dto.FileUploadResponse;
import com.glebgol.restapi.utils.constants.Constants;
import com.glebgol.restapi.utils.FileUploadUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("api/v1/")
public class FileUploadController {
    protected final String uploadPath = Constants.FILE_UPLOAD_PATH;
    protected final String downloadUri = Constants.DOWNLOAD_URI;

    @PostMapping(value = "/uploadFile", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            log.warn("Multipart file is empty");
            return ResponseEntity.badRequest().build();
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(uploadPath, multipartFile);

        FileUploadResponse response = FileUploadResponse.builder()
                .fileName(fileName)
                .size(multipartFile.getSize())
                .downloadUri(downloadUri + fileName)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
