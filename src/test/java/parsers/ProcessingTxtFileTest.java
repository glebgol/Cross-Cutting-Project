package parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProcessingTxtFileTest {

    @Test
    void CalculateLine_Test1() {
        // Assert
        var line = "1 + 2";
        var expectedResult = "3.0";

        // Act
        var result = ProcessingTxtFile.CalculateLine(line);

        // Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void CalculateLine_Test2() {
        // Assert
        var line = "(1 + 2) + 3.0";
        var expectedResult = "6.0";

        // Act
        var result = ProcessingTxtFile.CalculateLine(line);

        // Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void CalculateLine_Test3() {
        // Assert
        var line = "((1 + 2) + 3.0) / 2 * 1";
        var expectedResult = "3.0";

        // Act
        var result = ProcessingTxtFile.CalculateLine(line);

        // Assert
        Assertions.assertEquals(expectedResult, result);
    }
}