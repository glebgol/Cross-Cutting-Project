package parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ArgumentProviders.CalculateTxtLineArgumentProvider;

class ProcessingTxtFileTest {
    @ParameterizedTest
    @ArgumentsSource(CalculateTxtLineArgumentProvider.class)
    public void CalculateLine_Test(CalculateTxtLineArgumentProvider.ArgForTesting arg) {
        // Arrange
        var line = arg.line;
        var expectedLine = arg.expectedLine;

        // Act
        var result = CalculationEngine.CalculateLine(line);

        // Assert
        Assertions.assertEquals(expectedLine, result);
    }
}