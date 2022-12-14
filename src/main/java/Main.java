import builder.FileReaderBuilder;
import ciphers.CryptoUtils;
import enums.FileExtension;
import exceptions.CryptoException;
import readers.JsonFileReader;
import readers.XmlFileReader;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    private static final String FirstKey = "qwsdcvbgfthyrdfw";
    private static final String SecondKey = "asdfghjkqewrtyto";

    private static final String DoubleEncryptedAndZippedFileName = "double_encrypted.zip";
    private static final String TxtOutputFileName = "output.txt";

    private static final String JsonFileName = "input.json";
    private static final String JsonOutputFileName = "output.json";

    private static final String XmlFileName = "input.xml";
    private static final String XmlOutputFileName = "output.xml";


    public static void main(String[] args) throws IOException, CryptoException, JAXBException {

        var builder = new FileReaderBuilder(FileExtension.Txt, DoubleEncryptedAndZippedFileName);

        builder.setEncrypting(FirstKey);
        builder.setEncrypting(SecondKey);
        builder.setZipping(true);

        var reader = builder.getFileReader();
        reader.calculate(TxtOutputFileName);

        var jsonReader = new JsonFileReader(JsonFileName);
        jsonReader.calculate(JsonOutputFileName);

        var xmlReader = new XmlFileReader(XmlFileName);
        xmlReader.calculate(XmlOutputFileName);


        CryptoUtils.decrypt("1234567812345678", "Files-Upload/qw.txt", "ss.txt");
    }
}