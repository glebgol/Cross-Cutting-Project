package com.glebgol.businesslogic.contracts.interfaces;

import com.glebgol.businesslogic.contracts.exceptions.CryptoException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public interface IFileReader {
    String getInputFilename();
    void write(IStream stream, String outputFilename) throws IOException, CryptoException;
    IStream read() throws IOException, CryptoException, JAXBException;
    IStream transform(IStream stream) throws IOException, CryptoException, JAXBException;
    IStream calculate(IStream stream) throws IOException, CryptoException, JAXBException;
    default void calculate(String outputFileName) throws IOException, CryptoException, JAXBException {
        IStream readingResult = read();
        IStream calculatedResult = calculate(readingResult);
        write(calculatedResult, outputFileName);
    }
    default void calculate(File outputFile) throws IOException, CryptoException, JAXBException {
        calculate(outputFile.getAbsolutePath());
    }
}
