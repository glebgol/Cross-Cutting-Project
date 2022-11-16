package readers;

import interfaces.IFileReader;

public abstract class FileReader implements IFileReader {
    protected String inputFilename;
    protected String outputFilename;
    protected IFileReader _reader;

    public FileReader(IFileReader reader) {
        _reader = reader;
        inputFilename = reader.getInputFilename();
        outputFilename = reader.getOutputFilename();
    }

    @Override
    public String getInputFilename() {
        return inputFilename;
    }

    @Override
    public String getOutputFilename() {
        return outputFilename;
    }
}
