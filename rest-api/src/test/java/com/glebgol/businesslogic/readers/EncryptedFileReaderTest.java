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

import static com.glebgol.testvalues.TestValues.*;

class EncryptedFileReaderTest {

    @Test
    void Calculate_EncryptedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(FIRST_KEY,
                new TxtFileReader(ENCRYPTED_TXT));
        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_DoubleEncryptedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(SECOND_KEY,
                new EncryptedFileReader(FIRST_KEY,
                        new TxtFileReader(TWICE_ENCRYPTED)));

        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
    @Test
    void Calculate_EncryptedXmlFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(FIRST_KEY,
                        new XmlFileReader(ENC_XML));

        List<String> expectedCalculationResultLines = StreamArguments.CalculatedXmlLines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_EncryptedJsonFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new EncryptedFileReader(FIRST_KEY,
                new JsonFileReader(ENC_JSON));

        List<String> expectedCalculationResultLines = StreamArguments.CalculatedJsonLines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}