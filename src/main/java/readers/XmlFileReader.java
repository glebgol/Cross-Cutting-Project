package readers;

import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;

import java.io.IOException;

public class XmlFileReader implements IFileReader {
    protected String inputFilename;
    protected String outputFilename;

    public XmlFileReader(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
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

    }

    @Override
    public IStream Read() throws IOException, CryptoException {
        return null;
    }

    @Override
    public IStream Transform(IStream stream) throws IOException, CryptoException {
        return null;
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        return null;
    }

    @Override
    public void WriteCalculated() throws IOException, CryptoException {

    }
}
