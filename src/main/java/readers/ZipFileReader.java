package readers;

import archivers.ArchivationFileManager;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;
import streams.ReadingResult;

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
        var unzippingResult = ArchivationFileManager.GetUnZipped(inputFilename);
        return _reader.Transform(unzippingResult);
    }

    @Override
    public IStream Transform(IStream stream) throws IOException, CryptoException {
        return _reader.Transform(stream);
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        return _reader.Calculate(stream);
    }
}