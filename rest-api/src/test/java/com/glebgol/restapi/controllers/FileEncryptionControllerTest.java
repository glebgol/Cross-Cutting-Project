package com.glebgol.restapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FileEncryptionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void encryptAndExpectOkStatusCode() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "(123 + 456) / 0".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/encrypt")
                        .file(file)
                        .queryParam("outputfile", "output.txt")
                        .queryParam("key", "1234123412341234"))
                .andExpect(status().is(200));
    }

    @Test
    public void calculateNotValidKeysAndExpectBadRequestStatusCode() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "(123 + 456) / 0".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/calculate")
                        .file(file)
                        .queryParam("outputfile", "output.txt")
                        .queryParam("key", "123"))
                .andExpect(status().is(400));
    }
}