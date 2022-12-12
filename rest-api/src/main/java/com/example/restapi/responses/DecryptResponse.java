package com.example.restapi.responses;

public class DecryptResponse {
    public String inputFilename;
    public String outputFilename;

    public String resultInfo;

    public DecryptResponse(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.resultInfo = String.format("%s file successfully decrypted and has been written to %s", inputFilename, outputFilename);
    }
}
