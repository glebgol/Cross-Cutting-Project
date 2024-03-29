package com.glebgol.restapi.restassured;

import com.glebgol.restapi.Urls.Urls;
import com.glebgol.testvalues.TestValues;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

import static com.glebgol.testvalues.TestValues.MESSAGE_FOR_NOT_UPLOADED_FILE;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiFileEncryptionTest extends BaseRestTest {
    public final String ENCRYPTION_URL = Urls.ENCRYPT_URL;
    @AfterMethod
    public void deleteFile() {
        deleteFile(TestValues.OUTPUT_TXT);
    }

    @Test
    public void encryptFile() {
        String fileName = TestValues.OUTPUT_TXT;
        Response response = given()
                .multiPart("inputFile", new File(TestValues.INPUT_FILE_TXT))
                .queryParam("outputFilename", fileName)
                .queryParam("key", TestValues.FIRST_KEY)
                .when().post(ENCRYPTION_URL);

        response
                .then().statusCode(201);

        String actualDownloadUri = response.getBody().jsonPath().get("downloadUri");

        boolean isFileExist = verifyFileIsUploaded(fileName);

        assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(fileName));
        assertEquals(actualDownloadUri, "/downloadFile/output.txt");
    }

    @Test
    public void encryptFileWithNotValidKeysReturnsBadRequestStatusCode() {
        String fileName = TestValues.OUTPUT_TXT;
        given()
                .multiPart("inputFile", new File(TestValues.INPUT_FILE_TXT))
                .queryParam("outputFilename", fileName)
                .queryParam("key", "hello")
                .when().post(ENCRYPTION_URL)
                .then().statusCode(400);
    }
}