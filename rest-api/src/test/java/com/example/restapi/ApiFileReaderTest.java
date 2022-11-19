package com.example.restapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


class ApiFileReaderTest {
    protected final String Uri = "http://localhost:8080/api/file-reader";
    protected final String NonExistingFilename = "NonExistingFilename";
    protected final String ExistingFilename = "rest-api/src/test/resources/double_encrypted.zip";
    protected final String InputFilename = "rest-api/src/test/resources/input-file.txt";
    protected final String OutputFilename = "rest-api/src/test/resources/output-file.txt";
    protected final String DecryptionKeys = "qwsdcvbgfthyrdfw,asdfghjkqewrtyto";
    protected final String DecryptionKey = "qwsdcvbgfthyrdfw";
    protected final String FileExtension = "txt";
    protected final String NotValidDecryptionKeys = "abra,cada,bra";
    @Test
    void Calculate_Returns_200_StatusCode() {
        var uri = String.format("%s/calculate/?inputfile=%s&outputfile=%s&iszipped=true&decryptionkeys=%s&extension=%s", Uri, ExistingFilename, OutputFilename, DecryptionKeys, FileExtension);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Calculate_NonExistingFilename_Returns_400_StatusCode() {
        var uri = String.format("%s/calculate/?inputfile=%s&outputfile=%s&iszipped=true&decryptionkeys=%s&extension=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKeys, FileExtension);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Calculate_NotValidKeys_Returns_400_StatusCode() {
        var uri = String.format("%s/calculate/?inputfile=%s&outputfile=%s&iszipped=true&decryptionkeys=%s&extension=%s", Uri, ExistingFilename, OutputFilename, NotValidDecryptionKeys, FileExtension);

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
    void Encrypt_NonExistingFilename_Returns_400_StatusCode() {
        var uri = String.format("%s/encrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKey);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Decrypt_Returns_200_StatusCode() {
        var uri = String.format("%s/decrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, InputFilename, OutputFilename, DecryptionKey);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Decrypt_NonExistingFilename_Returns_400_StatusCode() {
        var uri = String.format("%s/decrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKey);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Zip_Returns_200_StatusCode() {
        var uri = String.format("%s/zip/?inputfile=%s", Uri, InputFilename);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Zip_NonExistingFilename_Returns_400_StatusCode() {
        var uri = String.format("%s/zip/?inputfile=%s", Uri, NonExistingFilename);

        RestAssured
                .when()
                .get(uri)
                .then()
                .statusCode(400);
    }
}