import archivers.ArchivationFileManager;
import ciphers.CryptoUtils;
import exceptions.CryptoException;
import org.mariuszgromada.math.mxparser.Expression;
import readers.EncryptedFileReader;
import readers.TxtFileReader;
import readers.XmlFileReader;
import readers.ZipFileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CryptoException {
        XmlFileReader.foo(XmlFileReader.Temp.class);
    }
}