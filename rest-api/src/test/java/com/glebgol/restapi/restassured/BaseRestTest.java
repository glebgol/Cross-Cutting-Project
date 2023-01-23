package com.glebgol.restapi.restassured;


import org.testng.annotations.BeforeClass;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;


public abstract class BaseRestTest {
    @BeforeClass
    public static void setUp() {
        baseURI = "http://localhost/api/v1/";
        port = 8080;
    }

    public boolean verifyFileIsUploaded(String fileName) {
        String pathName = getPathName();
        File file = new File(pathName + fileName);
        return file.exists();
    }

    public static void deleteFile(String fileName) {
        String pathName = getPathName();
        File file = new File(pathName + fileName);
        file.delete();
    }

    private static String getPathName() {
        String userDir = System.getProperty("user.dir");
        return System.getProperty("user.dir").substring(0, userDir.lastIndexOf('\\')) + "\\Files-Upload\\";
    }
}
