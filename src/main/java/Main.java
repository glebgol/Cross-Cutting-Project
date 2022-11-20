import ciphers.CryptoUtils;
import exceptions.CryptoException;
import parsers.json.JsonExpression;
import parsers.json.JsonExpressionList;
import parsers.xml.XmlExpression;
import parsers.xml.XmlExpressionList;
import readers.XmlFileReader;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";
    public static void main(String[] args) throws IOException, CryptoException, JAXBException {
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


        CryptoUtils.Encrypt("qwsdcvbgfthyrdfw", "src/test/resources/input.json", "src/test/resources/enc_json.txt");

    }
}