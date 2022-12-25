package interfaces;

import exceptions.CryptoException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IFileReader {
    String getInputFilename();
    void write(IStream stream, String outputFilename) throws IOException, CryptoException;
    IStream read() throws IOException, CryptoException, JAXBException;
    IStream transform(IStream stream) throws IOException, CryptoException, JAXBException;
    IStream calculate(IStream stream) throws IOException, CryptoException, JAXBException;
    default void getResult(String outputFileName) throws IOException, CryptoException, JAXBException {
        var readingResult = read();
        var calculatedResult = calculate(readingResult);
        write(calculatedResult, outputFileName);
    }
}
