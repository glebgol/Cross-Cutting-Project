package ArgumentProviders;

import interfaces.IStream;
import parsers.json.ExpressionList;
import parsers.json.ExpressionObject;
import streams.JsonStream;
import streams.TxtStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static JsonStream JsonStream() {
        return new JsonStream(new ExpressionList( new ArrayList<>(
                List.of(
                        new ExpressionObject("12-100", "12-345", "12"),
                        new ExpressionObject("120/100", "120-345", "121"),
                        new ExpressionObject("12+100", "100-1", "12"),
                        new ExpressionObject("12*100", "12.1*4", "12/4")
                ))));
    }

    public static JsonStream JsonCalculationStream() {
        return new JsonStream(new ExpressionList(new ArrayList<>(
                List.of(
                        new ExpressionObject("-88.0", "-333.0", "12.0"),
                        new ExpressionObject("1.2", "-225.0", "121.0"),
                        new ExpressionObject("112.0", "99.0", "12.0"),
                        new ExpressionObject("1200.0", "48.4", "3.0")
                ))));
    }
}
