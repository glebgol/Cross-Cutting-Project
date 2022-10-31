package readers;

import ciphers.CryptoUtils;
import exceptions.CryptoException;
import interfaces.IFIleReader;
import interfaces.IStream;
import streams.ReadingResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EncryptedFileReader implements IFIleReader {

    protected String inputFilename;
    protected String outputFilename;
    protected IFIleReader _reader;
    protected String key;

    public EncryptedFileReader(String key, IFIleReader reader) {
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
        Encrypt();
    }

    @Override
    public IStream Read() throws CryptoException {
        var bytes = CryptoUtils.read(key, new File(inputFilename));
        var stringResult = new String(bytes, StandardCharsets.UTF_8);
        var arrayListOfStrings = new ArrayList<String>();
        var stringTokenizer = new StringTokenizer(stringResult, " ");
        while (stringTokenizer.hasMoreTokens()) {
            arrayListOfStrings.add(stringTokenizer.nextToken());
        }
        return new ReadingResult(arrayListOfStrings);
    }

    @Override
    public IStream Calculate() throws FileNotFoundException {
        return null;
    }

    private void Encrypt() throws CryptoException {
        CryptoUtils.Encrypt(key, new File(inputFilename), new File(outputFilename));
    }
}
