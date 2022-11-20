package readers;

import ArgumentProviders.StreamArguments;
import exceptions.CryptoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import streams.XmlStream;

import javax.xml.bind.JAXBException;
import java.io.IOException;

class EncryptedFileReaderTest {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";

    @Test
    void Calculate_EncryptedFile() throws IOException, CryptoException, JAXBException {
        // Arrange
        var reader = new EncryptedFileReader(FirstKey,
                new TxtFileReader("src/test/resources/encrypted.txt", "src/test/resources/output.txt"));
        var expectedCalculationResult = StreamArguments.CalculationResult();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var readingResult = reader.Read();
        var calculationResult = reader.Calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_DoubleEncryptedFile() throws IOException, CryptoException, JAXBException {
        // Arrange
        var reader = new EncryptedFileReader(SecondKey,
                new EncryptedFileReader(FirstKey,
                        new TxtFileReader("src/test/resources/double_encrypted.txt", "src/test/resources/output.txt")));

        var expectedCalculationResult = StreamArguments.CalculationResult();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var readingResult = reader.Read();
        var calculationResult = reader.Calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    //todo json and xml encrypted tests
    @Test
    void Calculate_EncryptedXmlFile() throws IOException, CryptoException, JAXBException {
        // Arrange
        var reader = new EncryptedFileReader(FirstKey,
                        new XmlFileReader("src/test/resources/enc_xml.txt", "src/test/resources/output.xml"));

        var expectedCalculationResultLines = StreamArguments.CalculatedXmlLines();

        // Act
        var readingResult = reader.Read();
        var calculationResult = (XmlStream) reader.Calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

}