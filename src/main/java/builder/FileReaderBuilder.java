package builder;

import enums.FileExtension;
import interfaces.IFileReader;
import interfaces.IFileReaderBuilder;
import readers.*;

import java.util.List;

public class FileReaderBuilder implements IFileReaderBuilder {
    protected IFileReader fileReader;
    protected String inputFilename;
    protected String outputFilename;
    public FileReaderBuilder(FileExtension extension, String inputFilename, String outputFilename) {
        if (extension == FileExtension.Txt) {
            fileReader = new TxtFileReader(inputFilename, outputFilename);
        }
        else if (extension == FileExtension.Json) {
            fileReader = new JsonFileReader(inputFilename, outputFilename);
        }
        else if (extension == FileExtension.Xml) {
            fileReader = new XmlFileReader(inputFilename, outputFilename);
        }
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    public FileReaderBuilder(String extension, String inputFilename, String outputFilename) {
        var fileExtension = extension.toUpperCase();
        if (fileExtension.equals(FileExtension.Txt.name().toUpperCase())) {
            fileReader = new TxtFileReader(inputFilename, outputFilename);
        }
        else if ((fileExtension.equals(FileExtension.Json.name().toUpperCase()))){
            fileReader = new JsonFileReader(inputFilename, outputFilename);
        }
        else {
            fileReader = new XmlFileReader(inputFilename, outputFilename);
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
