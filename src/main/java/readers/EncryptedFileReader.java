package readers;

import ciphers.CryptoUtils;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;

import java.io.IOException;

public class EncryptedFileReader extends FileReader {
    protected String key;

    public EncryptedFileReader(String key, IFileReader reader) {
        super(reader);
        this.key = key;
    }

    @Override
    public void Write(IStream stream) throws IOException, CryptoException {
        _reader.Write(stream);
    }

    @Override
    public IStream Read() throws CryptoException, IOException {
        var result = CryptoUtils.GetDecrypting(key, inputFilename);
        return _reader.Transform(result);
    }

    @Override
    public IStream Transform(IStream stream) throws CryptoException, IOException {
        var result = CryptoUtils.Decrypt(stream, key);
        return _reader.Transform(result);
    }

    @Override
    public IStream Calculate(IStream stream) throws IOException, CryptoException {
        return _reader.Calculate(stream);
    }

    @Override
    public void WriteCalculated() throws IOException, CryptoException {
        var readingResult = Read();
        var calculatedResult = Calculate(readingResult);
        Write(calculatedResult);
    }
}
