package readers;

import ArgumentProviders.StreamArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TxtFileReaderTest {
    @Test
    void Calculate() {
        // Arrange
        var spyReader = Mockito.spy(new TxtFileReader(null));
        var streamForCalculation = StreamArguments.ReadingResult();
        var expectedCalculationResult = StreamArguments.CalculationResult();
        var expectedCalculationResultLines = expectedCalculationResult.lines();

        // Act
        var calculationResult = spyReader.Calculate(streamForCalculation);
        var calculationResultLines = calculationResult.lines();

        // Assert
        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}