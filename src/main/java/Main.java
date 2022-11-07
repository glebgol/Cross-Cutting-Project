import ciphers.CryptoUtils;
import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import readers.EncryptedFileReader;
import readers.TxtFileReader;
import readers.ZipFileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {
        String key = "asdfghjkqewrtyto";
        CryptoUtils.Encrypt(key, "src/test/resources/encrypted.txt", "src/test/resources/double_encrypted.txt");
    }
}