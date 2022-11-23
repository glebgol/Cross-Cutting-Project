package com.example.restapi.ResponseEntities;

public class EncryptResponse {
    public String inputFilename;
    public String outputFilename;

    public String resultInfo;

    public EncryptResponse(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.resultInfo = String.format("%s file successfully encrypted and has been written to %s", inputFilename, outputFilename);
    }
}
