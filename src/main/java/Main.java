import builder.FileReaderBuilder;
import enums.FileExtension;
import exceptions.CryptoException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {
        String FirstKey = "qwsdcvbgfthyrdfw";
        String SecondKey = "asdfghjkqewrtyto";
        var builder = new FileReaderBuilder("src/test/resources/double_encrypted.zip", "output.txt");
        builder.setFileExtension(FileExtension.Txt);

        builder.setEncrypting(FirstKey);
        builder.setEncrypting(SecondKey);

        builder.setZipping();

        var reader = builder.getResult();

        reader.WriteCalculated();
    }
}