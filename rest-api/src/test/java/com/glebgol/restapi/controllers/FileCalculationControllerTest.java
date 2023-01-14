package com.glebgol.restapi.controllers;

import com.glebgol.testvalues.TestValues;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.glebgol.testvalues.TestValues.MESSAGE_FOR_NOT_UPLOADED_FILE;
import static io.restassured.RestAssured.given;

public class FileCalculationControllerTest extends BaseRestTest {
    public static final String CALCULATE_URL = "/calculate";
    @AfterAll
    public static void deleteFiles() {
        deleteFile(TestValues.OUTPUT_TXT);
        deleteFile(TestValues.OUTPUT_XML);
        deleteFile(TestValues.OUTPUT_JSON);
    }
    @Test
    public void calculateTwiceEncryptedAndZippedTxtFile() {
        String txtFileName = TestValues.OUTPUT_TXT;
        given()
                .multiPart("file", new File(TestValues.ZIPPED_AND_TWICE_ENCRYPTED_TXT))
                .queryParam("outputfile", txtFileName)
                .queryParam("decryptionkeys", TestValues.KEYS)
                .queryParam("iszipped", true)
                .queryParam("extension", "txt").log().all()
                .when().post(CALCULATE_URL)
                .then().statusCode(200);

        boolean isFileExist = verifyFileIsUploaded(txtFileName);

        Assertions.assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(txtFileName));
    }
    @Test
    public void calculateXmlFile() {
        String xmlFileName = TestValues.OUTPUT_XML;
        given()
                .multiPart("file", new File(TestValues.XML_FILE))
                .queryParam("outputfile", xmlFileName)
                .queryParam("iszipped", true)
                .queryParam("extension", "txt").log().all()
                .when().post(CALCULATE_URL)
                .then().statusCode(200);

        boolean isFileExist = verifyFileIsUploaded(xmlFileName);

        Assertions.assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(xmlFileName));
    }

    @Test
    public void calculateJsonFile() {
        String jsonFileName = TestValues.OUTPUT_JSON;
        given()
                .multiPart("file", new File(TestValues.JSON_FILE))
                .queryParam("outputfile", jsonFileName)
                .queryParam("decryptionkeys", TestValues.KEYS)
                .queryParam("iszipped", true)
                .queryParam("extension", "txt").log().all()
                .when().post(CALCULATE_URL)
                .then().statusCode(200);

        boolean isFileExist = verifyFileIsUploaded(jsonFileName);

        Assertions.assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(jsonFileName));
    }
}