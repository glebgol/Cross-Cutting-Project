package com.glebgol.restapi.restassured;

import com.glebgol.restapi.Urls.Urls;
import com.glebgol.testvalues.TestValues;
import io.restassured.response.Response;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;

import java.io.File;

import static com.glebgol.testvalues.TestValues.MESSAGE_FOR_NOT_UPLOADED_FILE;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiFileCalculationTest extends BaseRestTest {
    public static final String CALCULATE_URL = Urls.CALCULATE_URL;

    @AfterGroups(groups = "txt")
    public void deleteTxtFile() {
        deleteFile(TestValues.OUTPUT_TXT);
    }

    @AfterGroups(groups = "xml")
    public void deleteXmlFile() {
        deleteFile(TestValues.OUTPUT_XML);
    }

    @AfterGroups(groups = "json")
    public void deleteJsonFile() {
        deleteFile(TestValues.OUTPUT_JSON);
    }

    @Test(groups = "txt")
    public void calculateTwiceEncryptedAndZippedTxtFile() {
        String txtFileName = TestValues.OUTPUT_TXT;
        given()
                .multiPart("inputFile", new File(TestValues.TWICE_ENCRYPTED_ZIP))
                .queryParam("outputFilename", txtFileName)
                .queryParam("decryptionKeys", TestValues.KEYS)
                .queryParam("isZipped", true)
                .queryParam("extension", "txt").log().all()
                .when().post(CALCULATE_URL)
                .then().statusCode(201);

        boolean isFileExist = verifyFileIsUploaded(txtFileName);

        assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(txtFileName));
    }

    @Test(groups = "xml")
    public void calculateXmlFile() {
        String xmlFileName = TestValues.OUTPUT_XML;
        Response response = given()
                .multiPart("inputFile", new File(TestValues.XML_FILE))
                .queryParam("outputFilename", xmlFileName)
                .queryParam("isZipped", false)
                .queryParam("extension", "xml").log().all()
                .when().post(CALCULATE_URL);

        response
                .then().statusCode(201);

        String actualDownloadUri = response.getBody().jsonPath().get("downloadUri");

        boolean isFileExist = verifyFileIsUploaded(xmlFileName);

        assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(xmlFileName));
        assertEquals(actualDownloadUri, "/downloadFile/output.xml");
    }

    @Test(groups = "json")
    public void calculateJsonFile() {
        String jsonFileName = TestValues.OUTPUT_JSON;
        given()
                .multiPart("inputFile", new File(TestValues.JSON_FILE))
                .queryParam("outputFilename", jsonFileName)
                .queryParam("isZipped", false)
                .queryParam("extension", "json").log().all()
                .when().post(CALCULATE_URL)
                .then().statusCode(201);

        boolean isFileExist = verifyFileIsUploaded(jsonFileName);

        assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(jsonFileName));
    }
}