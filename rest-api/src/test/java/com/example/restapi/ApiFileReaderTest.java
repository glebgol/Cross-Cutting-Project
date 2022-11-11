package com.example.restapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


class ApiFileReaderTest {

    @Test
    void Calculate_Returns_200_StatusCode() {
        RestAssured
                .when()
                .get("http://localhost:8080/api/file-reader/calculate/?inputfile=D://Anton//Cross-Cutting-Project//double_encrypted.zip&outputfile=output2.txt&iszipped=true&decryptionkeys=qwsdcvbgfthyrdfw,asdfghjkqewrtyto")
                .then()
                .statusCode(200);
    }

    @Test
    void Calculate_Returns_400_StatusCode() {
    }

    @Test
    void Encrypt_Returns_200_StatusCode() {
    }

    @Test
    void Encrypt_Returns_400_StatusCode() {
    }

    @Test
    void Zip_Returns_200_StatusCode() {
    }

    @Test
    void Zip_Returns_400_StatusCode() {
    }
}