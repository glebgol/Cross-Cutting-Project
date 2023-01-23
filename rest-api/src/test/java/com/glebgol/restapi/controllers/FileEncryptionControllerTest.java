package com.glebgol.restapi.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class FileEncryptionControllerTest extends SpringBootTestBase {
    @Test
    public void encryptAndExpectOkStatusCode() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", FILE_NAME, "text/plain", "(123 + 456) / 0".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/encrypt")
                        .file(file)
                        .queryParam("outputfile", OUTPUT_FILE_NAME)
                        .queryParam("key", "1234123412341234"))
                .andExpect(status().is(200));
    }

    @Test
    public void calculateNotValidKeysAndExpectBadRequestStatusCode() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", FILE_NAME, "text/plain", "(123 + 456) / 0".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/calculate")
                        .file(file)
                        .queryParam("outputfile", OUTPUT_FILE_NAME)
                        .queryParam("key", "123"))
                .andExpect(status().is(400));
    }
}