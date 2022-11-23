package com.example.restapi.ResponseEntities;

public class ZipResponse {
    public String inputFilename;
    public String resultInfo;

    public ZipResponse(String inputFilename) {
        this.inputFilename = inputFilename;
        this.resultInfo = String.format("%s file successfully zipped", inputFilename);
    }
}
