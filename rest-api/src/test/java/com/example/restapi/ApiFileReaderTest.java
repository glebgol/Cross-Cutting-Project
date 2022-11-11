package com.example.restapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


class ApiFileReaderTest {
    private final String Uri = "http://localhost:8080/api/file-reader";
    private final String NonExistingFilename = "NonExistingFilename";
    private final String ExistingFilename = "rest-api/src/test/resources/double_encrypted.zip";
    private final String InputFilename = "rest-api/src/test/resources/input-file.txt";

    private final String OutputFilename = "rest-api/src/test/resources/output-file.txt";
    private final String DecryptionKeys = "qwsdcvbgfthyrdfw,asdfghjkqewrtyto";
    private final String DecryptionKey = "qwsdcvbgfthyrdfw";

    @Test
    void Calculate_Returns_200_StatusCode() {
        var uri = String.format("%s/calculate/?inputfile=%s&outputfile=%s&iszipped=true&decryptionkeys=%s", Uri, ExistingFilename, OutputFilename, DecryptionKeys);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Calculate_Returns_400_StatusCode() {
        var uri = String.format("%s/calculate/?inputfile=%s&outputfile=%s&iszipped=true&decryptionkeys=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKeys);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Encrypt_Returns_200_StatusCode() {
        var uri = String.format("%s/encrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, InputFilename, OutputFilename, DecryptionKey);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Encrypt_Returns_400_StatusCode() {
        var uri = String.format("%s/encrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKey);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Zip_Returns_200_StatusCode() {
    }

    @Test
    void Zip_Returns_400_StatusCode() {
    }
}