package com.glebgol.restapi.utils;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileDownloadUtil {
    Resource getFileAsResource(String fileName) throws IOException;
}
