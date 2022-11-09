package interfaces;

import enums.FileExtension;

public interface IFileReaderBuilder {
    void setEncrypting(String key);
    void setZipping();
    void setFileExtension(FileExtension extension);
    void setInputFilename(String inputFilename);
    void setOutputFilename(String outputFilename);
    IFileReader getResult();
}
