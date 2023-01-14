package com.glebgol.businesslogic.calculation;

import com.glebgol.providers.CalculateTxtLineArgumentProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;


class CalculationEngineTest {
    @ParameterizedTest
    @ArgumentsSource(CalculateTxtLineArgumentProvider.class)
    public void CalculateLine_Test(CalculateTxtLineArgumentProvider.ArgForTesting arg) {
        String line = arg.line;
        String expectedLine = arg.expectedLine;

        String result = CalculationEngine.calculateLine(line);

        Assertions.assertEquals(expectedLine, result);
    }
}