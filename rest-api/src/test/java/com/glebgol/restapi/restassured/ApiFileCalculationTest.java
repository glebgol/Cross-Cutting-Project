package com.glebgol.restapi.restassured;

import com.glebgol.restapi.Urls.Urls;
import com.glebgol.testvalues.TestValues;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.glebgol.testvalues.TestValues.MESSAGE_FOR_NOT_UPLOADED_FILE;
import static io.restassured.RestAssured.given;

public class ApiFileCalculationTest extends BaseRestTest {
    public static final String CALCULATE_URL = Urls.CALCULATE_URL;
    @AfterEach
    @Tag("group_txt")
    public void deleteTxtFile() {
        deleteFile(TestValues.OUTPUT_TXT);
    }

    @AfterEach
    @Tag("group_xml")
    public void deleteXmlFile() {
        deleteFile(TestValues.OUTPUT_XML);
    }

    @AfterEach
    @Tag("group_json")
    public void deleteJsonFile() {
        deleteFile(TestValues.OUTPUT_JSON);
    }

    @Test
    @Tag("group_txt")
    public void calculateTwiceEncryptedAndZippedTxtFile() {
        String txtFileName = TestValues.OUTPUT_TXT;
        given()
                .multiPart("file", new File(TestValues.TWICE_ENCRYPTED_ZIP))
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
    @Tag("group_xml")
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
    @Tag("group_json")
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