package interfaces;

import exceptions.CryptoException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IFileReader {
    String getInputFilename();
    void Write(IStream stream, String outputFilename) throws IOException, CryptoException;
    IStream Read() throws IOException, CryptoException, JAXBException;
    IStream Transform(IStream stream) throws IOException, CryptoException, JAXBException;
    IStream Calculate(IStream stream) throws IOException, CryptoException, JAXBException;
    void GetResult(String outputFileName) throws IOException, CryptoException, JAXBException;
}
