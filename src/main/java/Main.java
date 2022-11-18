import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import enums.FileExtension;
import exceptions.CryptoException;
import parsers.json.ExpressionList;
import parsers.json.ExpressionObject;
import readers.EncryptedFileReader;
import readers.JsonFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//        var reader = new JsonFileReader("input.json", "output.json");
//        var expList = new ExpressionList(new ArrayList<>(List.of(new ExpressionObject("12-100", "12-345", "12"))));
//        reader.WriteJsonExpressions(expList);

        CryptoUtils.Encrypt("1234567812345678", "output.json", "1.json");

        var reader = new EncryptedFileReader("1234567812345678", new JsonFileReader("1.json", "3.json"));
        var reading = reader.Read();
        reader.Write(reading);
        CryptoUtils.Decrypt("1234567812345678", "1.json", "2.json");
    }
}