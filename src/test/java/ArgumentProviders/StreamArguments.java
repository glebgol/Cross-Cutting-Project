package ArgumentProviders;

import streams.CalculationResult;
import streams.ReadingResult;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamArguments {
    public static ReadingResult ReadingResult() {
        return new ReadingResult(new ArrayList<>(
                Arrays.asList(
                        "123 + 1234.5",
                        "qwerty\r",
                        "23-1999*100",
                        "1000 -12"
                )));
    }

    public static CalculationResult CalculationResult() {
        return new CalculationResult(new ArrayList<>(
                Arrays.asList(
                    "1357.5",
                    "qwerty\r",
                    "-199877.0",
                    "988.0"
        )));
    }
}
