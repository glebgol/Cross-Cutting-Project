package ArgumentProviders;

import interfaces.IStream;
import streams.TxtStream;

import java.util.ArrayList;
import java.util.Arrays;

public class StreamArguments {
    public static IStream ReadingResult() {
        return new TxtStream(new ArrayList<>(
                Arrays.asList(
                        "123 + 1234.5",
                        "qwerty\r",
                        "23-1999*100",
                        "1000 -12"
                )));
    }

    public static IStream CalculationResult() {
        return new TxtStream(new ArrayList<>(
                Arrays.asList(
                    "1357.5",
                    "qwerty\r",
                    "-199877.0",
                    "988.0"
        )));
    }
}
