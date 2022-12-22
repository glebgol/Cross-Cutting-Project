package com.example.restapi;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


class ApiFileReaderTest {
    protected final String Uri = "http://localhost:8080/api/file-reader";
    protected final String CalculateUri = Uri + "/calculate/";
    protected final String NonExistingFilename = "NonExistingFilename";
    protected final String ExistingFilename = "rest-api/src/test/resources/double_encrypted.zip";
    protected final String InputFilename = "rest-api//src//test//resources//input-file.txt";
    protected final String OutputFilename = "rest-api//src//test//resources//output-file.txt";
    protected final String DecryptionKeys = "qwsdcvbgfthyrdfw,asdfghjkqewrtyto";
    protected final Object[] DecryptionKeysList = new String[] { "qwsdcvbgfthyrdfw", "asdfghjkqewrtyto" };
    protected final String DecryptionKey = "qwsdcvbgfthyrdfw";
    protected final String FileExtension = "txt";
    protected final String NotValidExtension = "yaml";
    protected final String NotValidDecryptionKeys = "abra,cada,bra";
    protected final boolean IsZipped = true;

    @Test
    void Calculate_ExistingFile_Returns_200_StatusCode() {
        given()
                .queryParam("inputfile", ExistingFilename)
                .queryParam("outputfile", OutputFilename)
                .queryParam("iszipped", IsZipped)
                .queryParam("decryptionkeys", DecryptionKeysList)
                .queryParam("extension", FileExtension)
                .expect().statusCode(200)
                .when()
                .get(CalculateUri).then().log().all();
    }

    @Test
    void Calculate_NonExistingFile_Returns_400_StatusCode() {
        given()
                .queryParam("inputfile", NonExistingFilename)
                .queryParam("outputfile", OutputFilename)
                .queryParam("iszipped", IsZipped)
                .queryParam("decryptionkeys", DecryptionKeysList)
                .queryParam("extension", FileExtension)
                .expect().statusCode(400)
                .when()
                .get(CalculateUri).then().log().all();
    }

    @Test
    void Calculate_NotValidKeys_Returns_400_StatusCode() {
        given()
                .queryParam("inputfile", ExistingFilename)
                .queryParam("outputfile", OutputFilename)
                .queryParam("iszipped", IsZipped)
                .queryParam("decryptionkeys", NotValidDecryptionKeys)
                .queryParam("extension", FileExtension)
                .expect().statusCode(400)
                .when()
                .get(CalculateUri).then().log().all();
    }

    @Test
    void Calculate_NotValidExtension_Returns_400_StatusCode() {
        given()
                .queryParam("inputfile", ExistingFilename)
                .queryParam("outputfile", OutputFilename)
                .queryParam("iszipped", IsZipped)
                .queryParam("decryptionkeys", DecryptionKeysList)
                .queryParam("extension", NotValidExtension)
                .expect().statusCode(400)
                .when()
                .get(CalculateUri).then().log().all();
    }

    @Test
    void Encrypt_ExistingFile_Returns_200_StatusCode() {
        var uri = String.format("%s/encrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, InputFilename, OutputFilename, DecryptionKey);

        when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Encrypt_NonExistingFile_Returns_400_StatusCode() {
        var uri = String.format("%s/encrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKey);

        when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Decrypt_ExistingFile_Returns_200_StatusCode() {
        var uri = String.format("%s/decrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, InputFilename, OutputFilename, DecryptionKey);

        when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Decrypt_NonExistingFile_Returns_400_StatusCode() {
        var uri = String.format("%s/decrypt/?inputfile=%s&outputfile=%s&key=%s", Uri, NonExistingFilename, OutputFilename, DecryptionKey);

        when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void Zip_ExistingFile_Returns_200_StatusCode() {
        var uri = String.format("%s/zip/?inputfile=%s", Uri, InputFilename);

        when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void Zip_NonExistingFile_Returns_400_StatusCode() {
        var uri = String.format("%s/zip/?inputfile=%s", Uri, NonExistingFilename);

        when()
                .get(uri)
                .then()
                .statusCode(400);
    }

    @Test
    void UnZip_ExistingFile_Returns_200_StatusCode() {
        var uri = String.format("%s/unzip/?inputfile=%s&outputfile=%s", Uri, InputFilename, OutputFilename);

        when()
                .get(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void UnZip_NonExistingFile_Returns_400_StatusCode() {
        var uri = String.format("%s/unzip/?inputfile=%s&outputfile=%s", Uri, NonExistingFilename, OutputFilename);

        when()
                .get(uri)
                .then()
                .statusCode(400);
    }
}