package com.glebgol.restapi.restassured;

import com.glebgol.restapi.Urls.Urls;
import com.glebgol.testvalues.TestValues;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.glebgol.testvalues.TestValues.MESSAGE_FOR_NOT_UPLOADED_FILE;
import static io.restassured.RestAssured.given;

public class ApiFileEncryptionTest extends BaseRestTest {
    public final String ENCRYPTION_URL = Urls.ENCRYPT_URL;
    @AfterEach
    public void deleteFile() {
        deleteFile(TestValues.OUTPUT_TXT);
    }

    @Test
    public void encryptFile() {
        String fileName = TestValues.OUTPUT_TXT;
        given()
                .multiPart("file", new File(TestValues.INPUT_FILE_TXT))
                .queryParam("outputfile", fileName)
                .queryParam("key", TestValues.FIRST_KEY)
                .when().post(ENCRYPTION_URL)
                .then().statusCode(200);

        boolean isFileExist = verifyFileIsUploaded(fileName);

        Assertions.assertTrue(isFileExist, MESSAGE_FOR_NOT_UPLOADED_FILE(fileName));
    }

    @Test
    public void encryptFileWithNotValidKeysReturnsBadRequestStatusCode() {
        String fileName = TestValues.OUTPUT_TXT;
        given()
                .multiPart("file", new File(TestValues.INPUT_FILE_TXT))
                .queryParam("outputfile", fileName)
                .queryParam("key", "hello")
                .when().post(ENCRYPTION_URL)
                .then().statusCode(400);
    }
}