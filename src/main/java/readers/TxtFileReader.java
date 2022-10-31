package readers;

import interfaces.IFIleReader;
import interfaces.IStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TxtFileReader implements IFIleReader {

    protected String inputFilename;
    protected String outputFilename;
    protected IFIleReader _reader;

    public TxtFileReader(IFIleReader reader) {
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
        _reader.Write(stream);
    }

    @Override
    public IStream Read() throws FileNotFoundException {
        return _reader.Read();
    }

    @Override
    public IStream Calculate() throws FileNotFoundException {
        return _reader.Calculate();
    }
}
