package readers;

import ArgumentProviders.StreamArguments;
import exceptions.CryptoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import streams.JsonStream;
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
                new TxtFileReader("src/test/resources/encrypted.txt"));
        var expectedCalculationResult = StreamArguments.CalculationResult();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var readingResult = reader.read();
        var calculationResult = reader.calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_DoubleEncryptedFile() throws IOException, CryptoException, JAXBException {
        // Arrange
        var reader = new EncryptedFileReader(SecondKey,
                new EncryptedFileReader(FirstKey,
                        new TxtFileReader("src/test/resources/double_encrypted.txt")));

        var expectedCalculationResult = StreamArguments.CalculationResult();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var readingResult = reader.read();
        var calculationResult = reader.calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
    @Test
    void Calculate_EncryptedXmlFile() throws IOException, CryptoException, JAXBException {
        // Arrange
        var reader = new EncryptedFileReader(FirstKey,
                        new XmlFileReader("src/test/resources/enc_xml.txt"));

        var expectedCalculationResultLines = StreamArguments.CalculatedXmlLines();

        // Act
        var readingResult = reader.read();
        var calculationResult = (XmlStream) reader.calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }

    @Test
    void Calculate_EncryptedJsonFile() throws IOException, CryptoException, JAXBException {
        // Arrange
        var reader = new EncryptedFileReader(FirstKey,
                new JsonFileReader("src/test/resources/enc_json.txt"));

        var expectedCalculationResultLines = StreamArguments.CalculatedJsonLines();

        // Act
        var readingResult = reader.read();
        var calculationResult = (JsonStream) reader.calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}