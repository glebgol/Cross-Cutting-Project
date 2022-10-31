package readers;

import interfaces.IFIleReader;
import interfaces.IStream;

public class ReaderBase implements IFIleReader {
    protected String inputFilename;
    protected String outputFilename;
    protected IFIleReader _reader;

    public ReaderBase(IFIleReader reader) {
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
    public void Write(IStream stream) {

    }

    @Override
    public IStream Read() {
        return null;
    }

    @Override
    public IStream Calculate() {
        return null;
    }
}
