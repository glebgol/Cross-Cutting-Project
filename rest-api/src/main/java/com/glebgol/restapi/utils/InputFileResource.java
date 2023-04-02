package com.glebgol.restapi.utils;

import com.glebgol.restapi.dto.ParamsDTO;
import com.glebgol.restapi.utils.impl.FileUploadUtilImpl;
import org.springframework.web.multipart.MultipartFile;

public class InputFileResource implements AutoCloseable {
    private final FileUploadUtil fileUploadUtil = new FileUploadUtilImpl();
    private final String inputFilename;
    private final String UPLOAD_PATH;

    public InputFileResource(String uploadPath, MultipartFile multipartFile) {
        fileUploadUtil.saveFile(uploadPath, multipartFile);
        inputFilename = multipartFile.getOriginalFilename();
        UPLOAD_PATH = uploadPath;
    }

    public InputFileResource(String uploadPath, ParamsDTO paramsDTO) {
        fileUploadUtil.saveFile(uploadPath, paramsDTO.getInputFile());
        inputFilename = paramsDTO.getInputFile().getOriginalFilename();
        UPLOAD_PATH = uploadPath;
    }

    @Override
    public void close() throws Exception {
        fileUploadUtil.deleteFile(UPLOAD_PATH, inputFilename);
    }
}
