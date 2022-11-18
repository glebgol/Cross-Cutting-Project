import builder.FileReaderBuilder;
import enums.FileExtension;
import exceptions.CryptoException;
import parsers.json.ExpressionList;
import parsers.json.ExpressionObject;
import readers.JsonFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
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
        var reader = new JsonFileReader("input.json", "output.json");
        var expl = new ExpressionList(new ArrayList<>(Arrays.asList(new ExpressionObject("12-100", "12-345", "12"))));
        reader.WriteJsonExpressions(expl);
    }
}