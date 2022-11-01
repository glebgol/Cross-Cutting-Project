package readers;

import archivers.ArchivationFileManager;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ZipFileReader implements IFileReader {

    protected String inputFilename;
    protected String outputFilename;
    protected IFileReader _reader;

    public ZipFileReader(IFileReader reader) {
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
        var unZipped = ArchivationFileManager.GetUnZipped(inputFilename);
        return unZipped;
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        var unZippingResult = Read();
        return _reader.Calculate(unZippingResult);
    }
}