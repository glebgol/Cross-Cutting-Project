package readers;

import interfaces.IFIleReader;
import interfaces.IStream;

public class TxtFileReader extends ReaderBase {

    public TxtFileReader(IFIleReader reader) {
        super(reader);
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
