package com.example.restapi.responses;

public class CalculateResponse {
    public String inputFilename;
    public String outputFilename;

    public String resultInfo;

    public CalculateResponse(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.resultInfo = String.format("%s file successfully calculated and has been written to %s", inputFilename, outputFilename);
    }
}
