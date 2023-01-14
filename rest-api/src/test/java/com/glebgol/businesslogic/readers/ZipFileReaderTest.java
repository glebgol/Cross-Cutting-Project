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

class ZipFileReaderTest {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";
    @Test
    void Calculate_ZippedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new ZipFileReader(
                new TxtFileReader(System.getProperty("user.dir") +"\\src\\test\\resources\\default.zip"));
        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_ZippedAndEncryptedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new ZipFileReader(
                new EncryptedFileReader(FirstKey,
                        new TxtFileReader(System.getProperty("user.dir") +"\\src\\test\\resources\\encrypted.zip")));
        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_ZippedAndDoubleEncryptedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new ZipFileReader(
                new EncryptedFileReader(SecondKey,
                        new EncryptedFileReader(FirstKey,
                                new TxtFileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\double_encrypted.zip"))));
        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}