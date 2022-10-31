package interfaces;

import exceptions.CryptoException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileReader {
    String getInputFilename();
    String getOutputFilename();
    void Write(IStream stream) throws IOException, CryptoException;
    IStream Read() throws FileNotFoundException, CryptoException;
    IStream Calculate(IStream stream) throws FileNotFoundException, CryptoException;
}
