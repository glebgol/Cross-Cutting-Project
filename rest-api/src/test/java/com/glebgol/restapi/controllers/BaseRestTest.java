package com.glebgol.restapi.controllers;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;


public abstract class BaseRestTest {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://localhost/api/v1/";
        port = 8080;
    }

    public boolean verifyFileIsUploaded(String fileName) {
        return true;
    }

    public void deleteFile(String fileName) {

    }
}
