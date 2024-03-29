package com.glebgol.businesslogic.readers;


import com.glebgol.businesslogic.contracts.interfaces.IFileReader;

public abstract class FileReader implements IFileReader {
    protected String inputFilename;
    protected IFileReader _reader;

    public FileReader(IFileReader reader) {
        _reader = reader;
        inputFilename = reader.getInputFilename();
    }

    @Override
    public String getInputFilename() {
        return inputFilename;
    }
}
