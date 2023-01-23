package com.glebgol.restapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FileCalculationControllerTest extends SpringBootTestBase{
    @Test
    public void calculateAndExpectOkStatusCode() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", FILE_NAME, "text/plain", "(123 + 456) / 0".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/calculate")
                        .file(file)
                        .queryParam("outputfile", OUTPUT_FILE_NAME)
                        .queryParam("extension", "txt"))
                .andExpect(status().is(200));
    }

    @Test
    public void calculateNotValidKeysAndExpectBadRequestStatusCode() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", FILE_NAME, "text/plain", "(123 + 456) / 0".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/calculate")
                        .file(file)
                        .queryParam("outputfile", OUTPUT_FILE_NAME)
                        .queryParam("decryptionkeys", "123")
                        .queryParam("extension", "txt"))
                .andExpect(status().is(400));
    }

}