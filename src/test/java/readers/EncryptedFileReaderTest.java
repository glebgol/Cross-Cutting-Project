package readers;

import ArgumentProviders.StreamArguments;
import exceptions.CryptoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptedFileReaderTest {

    @Test
    void Calculate_EncryptedFile() throws IOException, CryptoException {
        // Arrange
        var key = "qwsdcvbgfthyrdfw";
        var reader = new EncryptedFileReader(key, new TxtFileReader("src/test/resources/encrypted.txt", "src/test/resources/output.txt"));
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