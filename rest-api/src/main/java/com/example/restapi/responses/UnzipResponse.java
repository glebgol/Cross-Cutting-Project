package com.example.restapi.responses;

public class UnzipResponse {
    public String inputFilename;
    public String outputFilename;

    public String resultInfo;

    public UnzipResponse(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
        this.resultInfo = String.format("%s file successfully unzipped to %s", inputFilename, outputFilename);
    }
}
