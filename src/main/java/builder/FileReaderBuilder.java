package builder;

import enums.FileExtension;
import interfaces.IFileReader;
import interfaces.IFileReaderBuilder;
import readers.EncryptedFileReader;
import readers.JsonFileReader;
import readers.TxtFileReader;
import readers.ZipFileReader;

import java.util.List;

public class FileReaderBuilder implements IFileReaderBuilder {
    protected IFileReader fileReader;
    protected String inputFilename;
    protected String outputFilename;
    public FileReaderBuilder(FileExtension extension, String inputFilename, String outputFilename) {
        if (extension == FileExtension.Txt) {
            fileReader = new TxtFileReader(inputFilename, outputFilename);
        }
        else if (extension == FileExtension.Json){
            fileReader = new JsonFileReader(inputFilename, outputFilename);
        }
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
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
    public IFileReader getResult() {
        return fileReader;
    }
}
