package readers;

import ArgumentProviders.StreamArguments;
import exceptions.CryptoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ZipFileReaderTest {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";
    @Test
    void Calculate_ZippedFile() throws IOException, CryptoException {
        // Arrange
        var reader = new ZipFileReader(
                new TxtFileReader("src/test/resources/default.zip", "src/test/resources/output.txt"));
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
    void Calculate_ZippedAndEncryptedFile() throws IOException, CryptoException {
        // Arrange
        var reader = new ZipFileReader(
                new EncryptedFileReader(FirstKey,
                        new TxtFileReader("src/test/resources/encrypted.zip", "src/test/resources/output.txt")));
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
    void Calculate_ZippedAndDoubleEncryptedFile() throws IOException, CryptoException {
        // Arrange
        var reader = new ZipFileReader(
                new EncryptedFileReader(SecondKey,
                        new EncryptedFileReader(FirstKey,
                                new TxtFileReader("src/test/resources/double_encrypted.zip", "src/test/resources/output.txt"))));
        var expectedCalculationResult = StreamArguments.CalculationResult();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var readingResult = reader.Read();
        var calculationResult = reader.Calculate(readingResult);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}