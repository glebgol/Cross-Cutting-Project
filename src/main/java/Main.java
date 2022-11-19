import exceptions.CryptoException;
import parsers.json.JsonExpressionList;
import parsers.json.JsonExpression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";
    public static void main(String[] args) throws IOException, CryptoException {
//        String FirstKey = "qwsdcvbgfthyrdfw";
//        String SecondKey = "asdfghjkqewrtyto";
//        var builder = new FileReaderBuilder("double_encrypted.zip", "output.txt");
//        builder.setFileExtension(FileExtension.Txt);
//
//        builder.setEncrypting(FirstKey);
//        builder.setEncrypting(SecondKey);
//
//        builder.setZipping();
//
//        var reader = builder.getResult();
//
//        reader.WriteCalculated();
//        var reader = new JsonFileReader("input.json", "output.json");
//        var expList = new ExpressionList(new ArrayList<>(List.of(new ExpressionObject("12-100", "12-345", "12"))));
//        reader.WriteJsonExpressions(expList);

        var s = new JsonExpressionList(new ArrayList<>(List.of(
                new JsonExpression("12-100", "12-345", "12"),
                new JsonExpression("12-100", "12-345", "12"),
                new JsonExpression("12-100", "12-345", "12"),
                new JsonExpression("12-100", "12-345", "12"),
                new JsonExpression("12-100", "12-345", "12")
                )));

        s.WriteToJsonFile("new.json");
        s.Calculate().WriteToJsonFile("new.json");
    }
}