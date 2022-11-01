package readers;

import ciphers.CryptoUtils;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;
import streams.ReadingResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EncryptedFileReader implements IFileReader {

    protected String inputFilename;
    protected String outputFilename;
    protected IFileReader _reader;
    protected String key;

    public EncryptedFileReader(String key, IFileReader reader) {
        _reader = reader;
        inputFilename = reader.getInputFilename();
        outputFilename = reader.getOutputFilename();
        this.key = key;
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
    public IStream Read() throws CryptoException {
        var bytes = CryptoUtils.GetDecrypting(key, new File(inputFilename));
        var stringResult = new String(bytes, StandardCharsets.UTF_8);
        var arrayListOfStrings = new ArrayList<String>();
        var stringTokenizer = new StringTokenizer(stringResult, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            arrayListOfStrings.add(stringTokenizer.nextToken());
        }
        return new ReadingResult(arrayListOfStrings);
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        var readingResult = stream;
        return _reader.Calculate(readingResult);
    }
}
