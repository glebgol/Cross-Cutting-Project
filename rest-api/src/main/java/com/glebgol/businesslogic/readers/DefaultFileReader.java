package com.glebgol.businesslogic.readers;


import com.glebgol.businesslogic.contracts.interfaces.IFileReader;

public abstract class DefaultFileReader implements IFileReader {
    protected String inputFilename;
    public DefaultFileReader(String inputFilename) {
        this.inputFilename = inputFilename;
    }
    @Override
    public String getInputFilename() {
        return inputFilename;
    }
}
