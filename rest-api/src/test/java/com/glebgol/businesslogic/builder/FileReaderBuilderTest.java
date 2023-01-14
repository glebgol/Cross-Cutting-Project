package com.glebgol.businesslogic.builder;

import com.glebgol.businesslogic.contracts.exceptions.CryptoException;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IFileReaderBuilder;
import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.providers.StreamArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


class FileReaderBuilderTest {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";

    @Test
    public void twiceEncryptedAndZippedFileReaderBuilderTest() throws JAXBException, IOException, CryptoException {
        IFileReaderBuilder builder = new FileReaderBuilder("txt", System.getProperty("user.dir") + "\\src\\test\\resources\\double_encrypted.zip");
        builder.setEncrypting(FirstKey);
        builder.setEncrypting(SecondKey);
        builder.setZipping(true);

        IFileReader reader = builder.getFileReader();

        IStream expectedCalculationResult = StreamArguments.CalculationResult();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream readingResult = reader.read();
        IStream calculationResult = reader.calculate(readingResult);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}