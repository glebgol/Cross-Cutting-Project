package com.example.restapi.utils;

import java.io.File;

public class FileDeleteUtil {
    public static void deleteFile(String path, String fileName) {
        File file = new File(path + "\\" + fileName);
        file.delete();
    }
}
