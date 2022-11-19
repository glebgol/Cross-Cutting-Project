import exceptions.CryptoException;
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

        var s = new XmlExpressionList(List.of(
                new XmlExpression("12-100", "12-345", "12"),
                new XmlExpression("12-54545454", "12-345", "12"),
                new XmlExpression("12-5454534545", "12-3343", "12"),
                new XmlExpression("12-103652640", "12-546", "12"),
                new XmlExpression("12-645654654", "5645645-345", "12")
                ));

        s.WriteToXmlFile("new.xml");

        var reader = new XmlFileReader("new.xml", "output1.xml");
        reader.WriteCalculated();
    }
}