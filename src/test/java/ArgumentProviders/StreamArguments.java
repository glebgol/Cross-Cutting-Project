package ArgumentProviders;

import interfaces.IStream;
import parsers.json.JsonExpressionList;
import parsers.json.JsonExpression;
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
        return new JsonStream(new JsonExpressionList( new ArrayList<>(
                List.of(
                        new JsonExpression("12-100", "12-345", "12"),
                        new JsonExpression("120/100", "120-345", "121"),
                        new JsonExpression("12+100", "100-1", "12"),
                        new JsonExpression("12*100", "12.1*4", "12/4")
                ))));
    }

    public static JsonStream JsonCalculationStream() {
        return new JsonStream(new JsonExpressionList(new ArrayList<>(
                List.of(
                        new JsonExpression("-88.0", "-333.0", "12.0"),
                        new JsonExpression("1.2", "-225.0", "121.0"),
                        new JsonExpression("112.0", "99.0", "12.0"),
                        new JsonExpression("1200.0", "48.4", "3.0")
                ))));
    }

    public static ArrayList<String> CalculatedXmlLines() {
        var str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<xmlExpressionList>\n" +
                "    <expressions>\n" +
                "        <expression1>2.0</expression1>\n" +
                "        <expression2>4.0</expression2>\n" +
                "        <expression3>6.0</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>0.0</expression1>\n" +
                "        <expression2>1.0</expression2>\n" +
                "        <expression3>2.0</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>3.5</expression1>\n" +
                "        <expression2>2.5</expression2>\n" +
                "        <expression3>5.0</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>100.0</expression1>\n" +
                "        <expression2>16.0</expression2>\n" +
                "        <expression3>12.0</expression3>\n" +
                "    </expressions>\n" +
                "    <expressions>\n" +
                "        <expression1>9.0</expression1>\n" +
                "        <expression2>2.0</expression2>\n" +
                "        <expression3>2.0</expression3>\n" +
                "    </expressions>\n" +
                "</xmlExpressionList>\n";
        return new ArrayList<>(List.of(
                str.split("\n")));
    }

    public static ArrayList<String> CalculatedJsonLines() {
        var str = "{\n" +
                "  \"expressions\": [\n" +
                "    {\n" +
                "      \"expression1\": \"2.0\",\n" +
                "      \"expression2\": \"4.0\",\n" +
                "      \"expression3\": \"6.0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"expression1\": \"1.0\",\n" +
                "      \"expression2\": \"2.0\",\n" +
                "      \"expression3\": \"3.0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"expression1\": \"2.5\",\n" +
                "      \"expression2\": \"1.5\",\n" +
                "      \"expression3\": \"2.0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"expression1\": \"24.0\",\n" +
                "      \"expression2\": \"4.0\",\n" +
                "      \"expression3\": \"7.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"expression1\": \"1.0\",\n" +
                "      \"expression2\": \"1.0\",\n" +
                "      \"expression3\": \"3.0\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return new ArrayList<>(List.of(
                str.split("\n")));
    }
}
