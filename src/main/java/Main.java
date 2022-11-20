import builder.FileReaderBuilder;
import enums.FileExtension;
import exceptions.CryptoException;
import readers.JsonFileReader;
import readers.XmlFileReader;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";
    public static void main(String[] args) throws IOException, CryptoException, JAXBException {

        var builder = new FileReaderBuilder(FileExtension.Txt, "double_encrypted.zip", "output.txt");
        builder.setEncrypting(FirstKey);
        builder.setEncrypting(SecondKey);
        builder.setZipping(true);

        var reader = builder.getResult();
        reader.WriteCalculated();

        var jsonReader = new JsonFileReader("input.json", "output.json");
        jsonReader.WriteCalculated();

        var xmlReader = new XmlFileReader("input.xml", "output.xml");
        xmlReader.WriteCalculated();
    }
}