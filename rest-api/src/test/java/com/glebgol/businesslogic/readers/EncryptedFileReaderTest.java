package com.glebgol.businesslogic.readers;

import com.glebgol.businesslogic.contracts.exceptions.CryptoException;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.providers.StreamArguments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

class EncryptedFileReaderTest {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";

    @Test
    void Calculate_EncryptedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(FirstKey,
                new TxtFileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\encrypted.txt"));
        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_DoubleEncryptedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(SecondKey,
                new EncryptedFileReader(FirstKey,
                        new TxtFileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\double_encrypted.txt")));

        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
    @Test
    void Calculate_EncryptedXmlFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(FirstKey,
                        new XmlFileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\enc_xml.txt"));

        List<String> expectedCalculationResultLines = StreamArguments.CalculatedXmlLines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_EncryptedJsonFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(FirstKey,
                new JsonFileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\enc_json.txt"));

        List<String> expectedCalculationResultLines = StreamArguments.CalculatedJsonLines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}