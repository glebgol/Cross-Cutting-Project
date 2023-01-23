package com.glebgol.restapi.controllers;

import java.io.File;

public class FileDeleteUtil {
    public static void deleteFile(String fileName) {
        File file = new File("Files-Upload\\" + fileName);
        file.delete();
    }
}
