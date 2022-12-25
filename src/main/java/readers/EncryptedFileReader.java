package readers;

import ciphers.CryptoUtils;
import exceptions.CryptoException;
import interfaces.IFileReader;
import interfaces.IStream;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class EncryptedFileReader extends FileReader {
    protected String key;

    public EncryptedFileReader(String key, IFileReader reader) {
        super(reader);
        this.key = key;
    }

    @Override
    public void write(IStream stream, String outputFilename) throws IOException, CryptoException {
        _reader.write(stream, outputFilename);
    }

    @Override
    public IStream read() throws CryptoException, IOException, JAXBException {
        var result = CryptoUtils.getDecrypting(key, inputFilename);
        return _reader.transform(result);
    }

    @Override
    public IStream transform(IStream stream) throws CryptoException, IOException, JAXBException {
        var result = CryptoUtils.decrypt(stream, key);
        return _reader.transform(result);
    }

    @Override
    public IStream calculate(IStream stream) throws IOException, CryptoException, JAXBException {
        return _reader.calculate(stream);
    }
}
