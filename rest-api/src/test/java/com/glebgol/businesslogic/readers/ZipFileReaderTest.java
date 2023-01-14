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

class ZipFileReaderTest {
    @Test
    void Calculate_ZippedFile() throws IOException, CryptoException, JAXBException {
        IFileReader reader = new ZipFileReader(
                new TxtFileReader(DEFAULT_ZIP));
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
                new EncryptedFileReader(FIRST_KEY,
                        new TxtFileReader(ENCRYPTED_ZIP)));
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
                new EncryptedFileReader(SECOND_KEY,
                        new EncryptedFileReader(FIRST_KEY,
                                new TxtFileReader(TWICE_ENCRYPTED_ZIP))));
        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}