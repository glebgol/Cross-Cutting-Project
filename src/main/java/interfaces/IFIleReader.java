package interfaces;

import ciphers.CryptoUtils;
import exceptions.CryptoException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFIleReader {
    String getInputFilename();
    String getOutputFilename();
    void Write(IStream stream) throws IOException, CryptoException;
    IStream Read() throws FileNotFoundException, CryptoException;
    IStream Calculate() throws FileNotFoundException;
}
