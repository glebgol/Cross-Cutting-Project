package readers;

import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TxtFileReader implements IFileReader {

    protected String inputFilename;
    protected String outputFilename;
    protected IFileReader _reader;

    public TxtFileReader(IFileReader reader) {
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
    public void Write(IStream stream) throws IOException, CryptoException {
        _reader.Write(stream);
    }

    @Override
    public IStream Read() throws IOException, CryptoException {
        return _reader.Read();
    }

    @Override
    public IStream Transform(IStream stream) {
        return null;
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        return _reader.Calculate(stream);
    }
}
