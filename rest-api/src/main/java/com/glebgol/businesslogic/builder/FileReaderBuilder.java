package com.glebgol.businesslogic.builder;


import com.glebgol.businesslogic.contracts.enums.FileExtension;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IFileReaderBuilder;
import com.glebgol.businesslogic.readers.*;

import java.util.List;

public class FileReaderBuilder implements IFileReaderBuilder {
    protected IFileReader fileReader;
    protected String inputFilename;
    public FileReaderBuilder(String extension, String inputFilename) {
        String fileExtension = extension.toUpperCase();
        if (fileExtension.equals(FileExtension.Txt.name().toUpperCase())) {
            fileReader = new TxtFileReader(inputFilename);
        }
        else if ((fileExtension.equals(FileExtension.Json.name().toUpperCase()))){
            fileReader = new JsonFileReader(inputFilename);
        }
        else if ((fileExtension.equals(FileExtension.Xml.name().toUpperCase()))) {
            fileReader = new XmlFileReader(inputFilename);
        }
        else {
            throw new IllegalArgumentException("Not supported file extension :\"" + extension + "\"");
        }
        this.inputFilename = inputFilename;
    }

    @Override
    public void setEncrypting(String key) {
        fileReader = new EncryptedFileReader(key, fileReader);
    }

    @Override
    public void setEncrypting(List<String> keys) {
        if (keys != null) {
            for (var key : keys) {
                fileReader = new EncryptedFileReader(key, fileReader);
            }
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
