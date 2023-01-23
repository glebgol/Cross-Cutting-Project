package com.glebgol.businesslogic.builder;


import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IFileReaderBuilder;
import com.glebgol.businesslogic.readers.*;

import java.util.List;

public class FileReaderBuilder implements IFileReaderBuilder {
    protected IFileReader fileReader;
    protected String inputFilename;
    public FileReaderBuilder(String extension, String inputFilename) {
        String fileExtension = extension.toUpperCase();
        switch (fileExtension) {
            case "TXT" -> fileReader = new TxtFileReader(inputFilename);
            case "JSON" -> fileReader = new JsonFileReader(inputFilename);
            case "XML" -> fileReader = new XmlFileReader(inputFilename);
            default -> throw new IllegalArgumentException("Not supported file extension :\"" + extension + "\"");
        }
        this.inputFilename = inputFilename;
    }

    @Override
    public void setEncrypting(String key) {
        fileReader = new EncryptedFileReader(key, fileReader);
    }

    @Override
    public void setEncrypting(List<String> keys) {
        for (var key : keys) {
            setEncrypting(key);
        }
    }

    @Override
    public void setZipping(boolean isZip) {
        if (isZip) {
            fileReader = new ZipFileReader(fileReader);
        }
    }

    @Override
    public IFileReader getFileReader() {
        return fileReader;
    }
}
