package com.glebgol.restapi.controllers;

import com.glebgol.testvalues.TestValues;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileCalculationControllerTest extends BaseRestTest {
    public static final String CALCULATE_URL = "/calculate";
    @AfterAll
    public static void deleteFiles() {
        deleteFile(TestValues.OUTPUT_TXT);
    }
    @Test
    public void calculateTwiceEncryptedAndZippedTxtFile() {
        given()
                .multiPart("file", new File(TestValues.ZIPPED_AND_TWICE_ENCRYPTED_TXT))
                .queryParam("outputfile", TestValues.OUTPUT_TXT)
                .queryParam("decryptionkeys", TestValues.KEYS)
                .queryParam("iszipped", true)
                .queryParam("extension", "txt").log().all()
                .when().post(CALCULATE_URL)
                .then().statusCode(200);


        boolean isFileExist = verifyFileIsUploaded(TestValues.OUTPUT_TXT);
        Assert.assertTrue(isFileExist, "no");
    }
}