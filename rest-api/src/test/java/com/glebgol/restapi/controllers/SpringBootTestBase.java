package com.glebgol.restapi.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class SpringBootTestBase {
    @Autowired
    protected MockMvc mockMvc;
    protected final String FILE_NAME = "filename.txt";
    protected final String OUTPUT_FILE_NAME = "output.txt";

    @AfterEach
    public void deleteFile() {
        FileDeleteUtil.deleteFile(OUTPUT_FILE_NAME);
    }

    @AfterAll
    public static void deleteUploadFolder() {
        FileDeleteUtil.deleteFile("Files-Upload");

    }
}
