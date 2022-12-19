package readers;

import ArgumentProviders.StreamArguments;
import exceptions.CryptoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

class JsonFileReaderTest {
    @Test
    public void Calculate() throws IOException, CryptoException {
        // Arrange
        var spyReader = Mockito.spy(new JsonFileReader(null));
        var streamForCalculation = StreamArguments.JsonStream();
        var expectedCalculationResult = StreamArguments.JsonCalculationStream();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var calculationResult = spyReader.calculate(streamForCalculation);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}