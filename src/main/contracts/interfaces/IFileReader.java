package interfaces;

import exceptions.CryptoException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IFileReader {
    String getInputFilename();
    String getOutputFilename();
    void Write(IStream stream) throws IOException, CryptoException;
    IStream Read() throws IOException, CryptoException, JAXBException;
    IStream Transform(IStream stream) throws IOException, CryptoException, JAXBException;
    IStream Calculate(IStream stream) throws IOException, CryptoException, JAXBException;
    void WriteCalculated() throws IOException, CryptoException, JAXBException;
}
