package builder;

import enums.FileExtension;
import interfaces.IFileReader;
import interfaces.IFileReaderBuilder;
import readers.EncryptedFileReader;
import readers.TxtFileReader;
import readers.ZipFileReader;

public class FileReaderBuilder implements IFileReaderBuilder {
    protected IFileReader fileReader;
    protected String inputFilename;
    protected String outputFilename;
    @Override
    public void setEncrypting(String key) {
        fileReader = new EncryptedFileReader(key, fileReader);
    }

    @Override
    public void setZipping() {
        fileReader = new ZipFileReader(fileReader);
    }

    @Override
    public void setFileExtension(FileExtension extension) {
        if (extension == FileExtension.Txt) {
            fileReader = new TxtFileReader(inputFilename, outputFilename);
        }
    }

    @Override
    public void setInputFilename(String inputFilename) {
        this.inputFilename = inputFilename;
    }

    @Override
    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    @Override
    public IFileReader getResult() {
        return fileReader;
    }
}
