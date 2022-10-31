package readers;

import interfaces.IFIleReader;
import interfaces.IStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EncryptedFileReader implements IFIleReader {

    protected String inputFilename;
    protected String outputFilename;
    protected IFIleReader _reader;

    public EncryptedFileReader(IFIleReader reader) {
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

    @Override
    public void Write(IStream stream) throws IOException {

    }

    @Override
    public IStream Read() throws FileNotFoundException {
        return null;
    }

    @Override
    public IStream Calculate() throws FileNotFoundException {
        return null;
    }
}
